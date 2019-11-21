package com.waken.dorm.serviceImpl.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.ButtonResources;
import com.waken.dorm.common.form.resource.EditButtonsForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.ResourceView;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.dao.auth.ResourceMapper;
import com.waken.dorm.dao.auth.RoleMapper;
import com.waken.dorm.dao.auth.RoleResourceRelMapper;
import com.waken.dorm.dao.auth.UserPrivilegeMapper;
import com.waken.dorm.service.auth.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ResourceServiceImpl
 * @Description ResourceServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/21 19:58
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    private final ResourceMapper resourceMapper;
    private final RoleResourceRelMapper roleResourceRelMapper;
    private final RoleMapper roleMapper;
    private final UserPrivilegeMapper userPrivilegeMapper;
    private final TreeUtil treeUtil;

    /**
     * 新增或修改资源
     *
     * @param editResourceForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Resource saveResource(EditResourceForm editResourceForm) {
        this.validate(editResourceForm);
        int count;
        Resource resource = new Resource();
        BeanMapper.copy(editResourceForm, resource);
        if (StringUtils.isBlank(editResourceForm.getPkResourceId())) {
            log.info("service: 新增资源开始");
            String pkResourceId = UUIDSequence.next();
            if (StringUtils.isBlank(editResourceForm.getParentId())) {
                //根节点是父级
                resource.setParent(Boolean.TRUE);
                resource.setParentId(Constant.ROOT);
            } else {
                resource.setParent(Boolean.FALSE);
                resource.setParentId(editResourceForm.getParentId());
                this.updateParent(editResourceForm.getParentId());
            }
            int resourceNo = this.getResourceNo(editResourceForm);
            resource.setPerms(CodeEnum.MENU.getMsg());
            resource.setSort(resourceNo);
            resource.setPkResourceId(pkResourceId);
            resource.setStatus(CodeEnum.ENABLE.getCode());
            count = resourceMapper.insert(resource);
            Assert.isFalse(Constant.ZERO == count);
            //新增的资源必须与超级管理员关联
            this.addResourceRoleRel(pkResourceId, CodeEnum.MENU.getCode());
        } else {//修改
            log.info("service: 更新资源开始");
            resourceMapper.updateById(resource);
        }
        EditButtonsForm editForm = new EditButtonsForm();
        editForm.setParentId(resource.getPkResourceId());
        editForm.setEditButtonsList(editResourceForm.getButtonResourcesList());
        this.batchSaveButton(editForm);
        return resource;
    }

    /**
     * 删除资源
     *
     * @param deleteForm
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteResource(DeleteForm deleteForm) {
        log.info("service: 删除资源开始");
        List<String> resourceIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        List<String> delResourceIds = this.getAllToDelIds(resourceIds);
        // 物理删除
        if (CodeEnum.YES.getCode().equals(delStatus)) {
            this.deleteResourceRel(delResourceIds);
            // 删除资源本身
            resourceMapper.deleteBatchIds(delResourceIds);
        } else if (CodeEnum.NO.getCode().equals(delStatus)) {
            this.deleteResourceRel(delResourceIds);
            Map toUpdateStatusMap = DormUtil.getToUpdateStatusMap(delResourceIds, UserManager.getCurrentUserId());
            resourceMapper.batchUpdateStatus(toUpdateStatusMap);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 删除所有的资源关联
     *
     * @param delResourceIds
     */
    private void deleteResourceRel(List<String> delResourceIds) {
        //删除与角色的关联
        List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectList(null);
        List<String> toDelPkIds = Lists.newArrayList();
        if (null != roleResourceRelList && !roleResourceRelList.isEmpty()) {
            roleResourceRelList.stream().forEach(rel -> {
                if (delResourceIds.contains(rel.getResourceId())) {
                    toDelPkIds.add(rel.getPkRoleResourceId());
                }
            });
        }
        if (null != toDelPkIds && !toDelPkIds.isEmpty()) {
            roleResourceRelMapper.deleteBatchIds(toDelPkIds);
        }
        //删除与用户的关联
        List<String> toDelPkPrivilegeIds = Lists.newArrayList();
        List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectList(new LambdaQueryWrapper<UserPrivilege>()
            .eq(UserPrivilege::getSubjectType,CodeEnum.MENU.getCode())
            .or()
            .eq(UserPrivilege::getSubjectType,CodeEnum.BUTTON.getCode())
        );

        if (null != userPrivilegeList && !userPrivilegeList.isEmpty()) {
            userPrivilegeList.stream().forEach(userPrivilege -> {
                if (delResourceIds.contains(userPrivilege.getSubjectId())) {
                    toDelPkPrivilegeIds.add(userPrivilege.getPkPrivilegeId());
                }
            });
        }
        if (null != toDelPkPrivilegeIds && !toDelPkPrivilegeIds.isEmpty()) {
            userPrivilegeMapper.deleteBatchIds(toDelPkPrivilegeIds);
        }
    }

    /**
     * 得到所有需要删除的资源，包括子节点资源
     *
     * @param resourcesIds
     * @return
     */
    private List<String> getAllToDelIds(List<String> resourcesIds) {
        List<Resource> resourceList = resourceMapper.selectList(new LambdaQueryWrapper<Resource>()
                .eq(Resource::getStatus, CodeEnum.ENABLE.getCode())
        );
        Map<String, String> idAndPidMap = resourceList.stream().collect(Collectors.toMap(Resource::getPkResourceId, Resource::getParentId));
        return TreeUtil.getNodesByIds(idAndPidMap, resourcesIds);
    }

    /**
     * 查询资源树集合
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public List<Tree<ResourceView>> getResourcesTree(String userId, String roleId) {
        //获得用户用户或者角色管理的资源，用于将对应的资源设置为选中状态（checked == true）
        Map<String, String> var4 = this.getSubjectResource(userId, roleId);

        //获取到所有的资源信息 null表示查询出所有的状态的资源用于资源树管理查询 1表示查询生效的资源是用于查询用户或者角色关联的资源
        List<ResourceView> allResource;
        if (StringUtils.isEmpty(userId) && StringUtils.isEmpty(roleId)){
            allResource = resourceMapper.selectResourceView(1);
        }else {
            allResource = resourceMapper.selectResourceView(null);
        }


        //将资源集合转换成树形集合对象
        List<Tree<ResourceView>> allResourceTree = this.getResourceTreeView(allResource, var4);

        return treeUtil.toTree(allResourceTree,Constant.ROOT);
    }

    /**
     * 如果status==null表示获取资源管理所需要的所有资源，或者获得用户用户或者角色管理的资源
     *
     * @param userId
     * @param roleId
     * @return
     */
    private Map<String, String> getSubjectResource(String userId, String roleId) {
        //查询资源管理所需要的资源
        List<UserMenuView> var3 = null;
        if (StringUtils.isNotBlank(roleId)) {
            var3 = userPrivilegeMapper.selectRoleResources(roleId);
        } else if (StringUtils.isNotBlank(userId)) {
            var3 = userPrivilegeMapper.selectUserResources(userId,null);
        }
        if (null == var3 || var3.isEmpty()) {
            return new HashMap<>();
        }
        return var3.stream().collect(Collectors.toMap(UserMenuView::getPkResourceId, UserMenuView::getResourceName));
    }

    /**
     * 将资源集合转换成树形集合对象
     *
     * @param var1 资源集合
     * @param var2 key为与角色或者用户管理的主键id
     * @return
     */
    private List<Tree<ResourceView>> getResourceTreeView(List<ResourceView> var1, Map<String, String> var2) {
        List<Tree<ResourceView>> allTree = new ArrayList<>();
        //查询到当前登录用户
        Tree<ResourceView> tree;
        ResourceView resourceView;
        Iterator<ResourceView> var6 = var1.iterator();
        while (var6.hasNext()) {
            tree = new Tree<>();
            resourceView = var6.next();
            if (null != var2 && !var2.isEmpty()) {
                if (var2.containsKey(resourceView.getPkResourceId())) {
                    tree.setChecked(true);
                }
            }
            //TODO
            if (resourceView.isParent()){
                tree.setChecked(false);
            }
            tree.setId(resourceView.getPkResourceId());
            tree.setParentId(resourceView.getParentId());
            tree.setKey(resourceView.getPkResourceId());
            tree.setTitle(resourceView.getResourceName());
            tree.setIcon(resourceView.getResourceIcon());
            tree.setSort(resourceView.getSort());
            tree.setLeaf(!resourceView.isParent());
            tree.setAttribute(resourceView);
            tree.setOpen(true);
            allTree.add(tree);
        }
        return allTree;
    }

    /**
     * 通过资源id查询资源
     *
     * @param resourceId
     * @return
     */
    @Override
    public Resource selectById(String resourceId) {
        return resourceMapper.selectById(resourceId);
    }

    /**
     * 通过资源id查询对应的按钮权限
     *
     * @param resourceId
     * @return
     */
    @Override
    public List<ButtonResources> selectButtonByResourceId(String resourceId) {

        return resourceMapper.selectButtonByResourceId(resourceId);
    }

    /**
     * 批量保存按钮资源
     *
     * @param editForm
     * @return
     */
    @Override
    public int batchSaveButton(EditButtonsForm editForm) {
        int count = 0;
        if (editForm == null || editForm.getEditButtonsList() == null || editForm.getEditButtonsList().isEmpty()){
            return count;
        }
        List<ButtonResources> editButtonsList = editForm.getEditButtonsList();
        this.validate(editForm);
        List<ButtonResources> addFormList = editButtonsList.stream().filter(buttonResource -> StringUtils.isEmpty(buttonResource.getPkResourceId())).collect(Collectors.toList());
        if (addFormList != null && !addFormList.isEmpty()){
            if (!this.saveBatch(this.getResourceList(addFormList,editForm.getParentId()))){
                throw new ServerException("保存失败");
            }
            editButtonsList.removeAll(addFormList);
        }
        if (editButtonsList != null && !editButtonsList.isEmpty()) {
            if (!this.updateBatchById(this.getResourceList(editButtonsList,editForm.getParentId()))){
                throw new ServerException("保存失败");
            }
        }
        return count;
    }

    private List<Resource> getResourceList(List<ButtonResources> editButtonsList,String parentId){
        List<Resource> resourceList = new ArrayList<>(editButtonsList.size());
        editButtonsList.stream().forEach(buttonResource -> {
            Resource resource = new Resource();
            BeanMapper.copy(buttonResource,resource);
            resource.setParentId(parentId);
            resource.setParent(false);
            resource.setResourceType(CodeEnum.BUTTON.getCode());
            resource.setStatus(CodeEnum.ENABLE.getCode());
            resourceList.add(resource);
        });
        return resourceList;
    }

    private void validate(EditButtonsForm editButtonsForm){
        List<ButtonResources> editList = editButtonsForm.getEditButtonsList();
        if (StringUtils.isEmpty(editButtonsForm.getParentId())){
            throw new ServerException("菜单资源为空！");
        }
        if (editList == null || editList.isEmpty()){
            return;
        }
        List<Resource> resourceList = resourceMapper.selectList(new LambdaQueryWrapper<Resource>()
                .eq(Resource::getResourceType, CodeEnum.BUTTON.getCode())
                .eq(Resource::getStatus, CodeEnum.ENABLE.getCode())
        );
        Map<String, String> nameAndIdMap = resourceList.stream()
                .collect(Collectors.toMap(Resource::getResourceName, Resource::getPkResourceId));

        Map<String, String> permsAndIdMap = resourceList.stream()
                .filter(resource -> !StringUtils.equals(CodeEnum.MENU.getMsg(),resource.getPerms()))
                .collect(Collectors.toMap(Resource::getPerms, Resource::getPkResourceId));
        editList.stream().forEach(editForm -> {
            String resourceId = editForm.getPkResourceId();
            String resourceName = editForm.getResourceName();
            String perms = editForm.getPerms();
            if (StringUtils.isEmpty(editForm.getPkResourceId())) {
                //新增验证
                Assert.notNull(resourceName);
                if (nameAndIdMap.containsKey(resourceName)){
                    throw new ServerException("按钮资源名称重复!");
                }
                if (permsAndIdMap.containsKey(perms)){
                    throw new ServerException("按钮权限名称重复!");
                }
            } else {
                if (StringUtils.isNotEmpty(resourceName)) {
                    this.checkDuplicate(nameAndIdMap.get(resourceName),resourceId,"按钮资源名称重复!");
                }
                if (StringUtils.isNotEmpty(perms)) {
                    this.checkDuplicate(permsAndIdMap.get(perms),resourceId,"按钮权限名称重复!");
                }
            }
        });

    }
    /**
     * 编辑资源时验证
     *
     * @param editForm
     */
    private void validate(EditResourceForm editForm) {
        List<Resource> resourceList = resourceMapper.selectList(new LambdaQueryWrapper<Resource>()
                .eq(Resource::getResourceType, CodeEnum.MENU.getCode())
                .eq(Resource::getStatus, CodeEnum.ENABLE.getCode())
        );
        Map<String, String> urlAndIdMap = resourceList.stream()
                .collect(Collectors.toMap(Resource::getResourceUrl, Resource::getPkResourceId));
        Map<String, String> nameAndIdMap = resourceList.stream()
                .collect(Collectors.toMap(Resource::getResourceName, Resource::getPkResourceId));

        String resourceId = editForm.getPkResourceId();
        String resourceName = editForm.getResourceName();
        String resourceUrl = editForm.getResourceUrl();
        if (StringUtils.isEmpty(editForm.getPkResourceId())) {
            //新增验证
            Assert.notNull(resourceName);
            if (StringUtils.isNotBlank(resourceUrl)) {
                if (urlAndIdMap.containsKey(resourceUrl)){
                    throw new ServerException("URL重复!");
                }
            }
            if (nameAndIdMap.containsKey(resourceName)){
                throw new ServerException("资源名称重复!");
            }
        } else {
            if (StringUtils.isNotEmpty(resourceName)) {
                this.checkDuplicate(nameAndIdMap.get(resourceName),resourceId,"资源名称重复!");
            }
            if (StringUtils.isNotEmpty(resourceUrl)) {
                this.checkDuplicate(urlAndIdMap.get(resourceUrl),resourceId,"URL重复!");
            }
        }

    }

    /**
     * 验证重复
     * @param id
     * @param oldPkId
     */
    private void checkDuplicate(String id,String oldPkId,String errorMsg){
        if (StringUtils.isNotEmpty(id) && !StringUtils.equals(id, oldPkId)) {
            throw new ServerException(errorMsg);
        }
    }

    /**
     * 检验新增或者修改的参数是否已经存在
     *
     * @param column
     * @param params
     * @param addOrUpdate
     */
    private void checkExist(String column, Object params, int addOrUpdate) {
        List<Resource> resourceList = resourceMapper.selectList(new QueryWrapper<Resource>()
                .eq(column, params)
                .eq("status", CodeEnum.ENABLE.getCode())
        );
        if (1 == addOrUpdate) {
            Assert.isNull(resourceList,resourceList.isEmpty(),"已经存在相同的" + params);
        } else if (2 == addOrUpdate) {
            Assert.isNull(resourceList,resourceList.size() <= Constant.ONE,"已经存在相同的" + params);
        }

    }

    /**
     * 新增的资源必须与超级管理员关联
     *
     * @param resourceId
     */
    private void addResourceRoleRel(String resourceId, int resourceType) {
        Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, Constant.SuperAdmin)
        );
        if (null != role) {
            RoleResourceRel roleResourceRel = new RoleResourceRel();
            roleResourceRel.setRoleId(role.getPkRoleId());
            roleResourceRel.setResourceId(resourceId);
            roleResourceRel.setResourceType(resourceType);
            roleResourceRelMapper.insert(roleResourceRel);
        }
    }

    /**
     * 获取资源序号
     *
     * @param editResourceForm
     * @return
     */
    private int getResourceNo(EditResourceForm editResourceForm) {
//        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<Resource>();
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isEmpty(editResourceForm.getParentId())) {
            wrapper.eq("parent_id", Constant.ROOT);
        } else {
            wrapper.eq("parent_id", editResourceForm.getParentId());
        }
        wrapper.eq("status", CodeEnum.ENABLE.getCode());
        int count = resourceMapper.selectCount(wrapper);
        return count;
    }

    /**
     * 更新父级（isprarent）字段
     *
     * @param parentId
     */
    private void updateParent(String parentId) {
        Resource resource = resourceMapper.selectOne(new LambdaQueryWrapper<Resource>()
                .eq(Resource::getPkResourceId, parentId)
        );
        if (null != resource) {
            if (false == resource.getParent()) {
                resource.setParent(Boolean.TRUE);
                resourceMapper.updateById(resource);
            }
        }
    }
}
