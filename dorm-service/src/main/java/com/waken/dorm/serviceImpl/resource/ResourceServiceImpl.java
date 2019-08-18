package com.waken.dorm.serviceImpl.resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.ResourceView;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.dao.resource.ResourceMapper;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.role.RoleResourceRelMapper;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.resource.ResourceService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class ResourceServiceImpl extends BaseServerImpl implements ResourceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    RoleResourceRelMapper roleResourceRelMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserPrivilegeMapper userPrivilegeMapper;

    /**
     * 新增或修改资源
     *
     * @param editResourceForm
     * @return
     */
    @Transactional
    @Override
    public Resource saveResource(EditResourceForm editResourceForm) {
        this.editResourceValidate(editResourceForm);
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count;
        Resource resource = new Resource();
        BeanMapper.copy(editResourceForm, resource);
        resource.setLastModifyTime(curDate);
        resource.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editResourceForm.getPkResourceId())) {//新增
            logger.info("service: 新增资源开始");
            String pkResourceId = UUIDUtils.getPkUUID();
            if (StringUtils.isEmpty(editResourceForm.getParentId())) {
                resource.setIsParent(Boolean.FALSE);
                resource.setParentId(Constant.ROOT);
            } else {
                resource.setIsParent(Boolean.FALSE);
                resource.setParentId(editResourceForm.getParentId());
                this.updateParent(editResourceForm.getParentId());
            }
            int resourceNo = this.getResourceNo(editResourceForm);
            resource.setSort(resourceNo);
            resource.setPkResourceId(pkResourceId);
            resource.setStatus(CodeEnum.ENABLE.getCode());
            resource.setCreateTime(curDate);
            resource.setCreateUserId(userId);
            count = resourceMapper.insert(resource);
            if (Constant.ZERO == count) {
                throw new ServerException("新增资源失败");
            }
            //新增的资源必须与超级管理员关联
            this.addResourceRoleRel(pkResourceId, editResourceForm.getResourceType());
        } else {//修改
            logger.info("service: 更新资源开始");
            resourceMapper.updateById(resource);
        }
        return resource;
    }

    /**
     * 删除资源
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteResource(DeleteForm deleteForm) {
        logger.info("service: 删除资源开始");
        List<String> resourceIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        List<String> delResourceList = this.getAllToDelIds(resourceIds);
        logger.info("需要删除的资源id: " + delResourceList.toString());
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            //TODO 删除资源与角色的关联 删除资源与人的关联
            List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectByResourceIds(delResourceList);
            if (!roleResourceRelList.isEmpty()) {
                StringBuffer sb = new StringBuffer();
                for (RoleResourceRel roleResourceRel : roleResourceRelList) {
                    sb.append(roleResourceRel.getResourceId());
                }
                if (StringUtils.isEmpty(sb.toString())) {//删除资源与角色关联
                    roleResourceRelMapper.deleteBatchIds(delResourceList);
                } else {
                    throw new ServerException("删除资源失败，原因是以下资源与角色关联生效中：" + sb.toString());
                }
            }
            // 删除资源本身
            int count = resourceMapper.deleteBatchIds(delResourceList);
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else if (CodeEnum.NO.getCode() == delStatus) {
            int count = resourceMapper.batchUpdateStatus(getToUpdateStatusMap(delResourceList, CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
            roleResourceRelMapper.deleteBatchIds(resourceIds);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 得到所有需要删除的资源，包括子节点资源
     *
     * @param resourcesIds
     * @return
     */
    private List<String> getAllToDelIds(List<String> resourcesIds) {
        if (null == resourcesIds && resourcesIds.isEmpty()) {
            throw new ServerException("入参为空！");
        }
        List<Resource> resourceList = resourceMapper.selectList(new EntityWrapper<Resource>()
                .eq("status", CodeEnum.ENABLE.getCode())
        );
        Map<String, String> idAndPidMap = resourceList.stream().collect(Collectors.toMap(Resource::getPkResourceId, Resource::getParentId));
        return TreeUtil.getNodesByIds(idAndPidMap, resourcesIds);
    }

    /**
     * 查询资源树集合
     * @param status
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public List<Tree<ResourceView>> getAllResourceTree(Integer status,String userId,String roleId) {
        //获取到所有的资源信息 null表示查询出所有的状态的资源用于资源树管理查询 1表示查询生效的资源是用于查询用户或者角色关联的资源
        List<ResourceView> allResource = resourceMapper.selectResourceView(status);

        //获得用户用户或者角色管理的资源
        Map<String,String> var5 = this.getSubjectResource(userId,roleId);

        //将资源集合转换成树形集合对象
        List<Tree<ResourceView>> allResourceTree = this.getResourceTreeView(allResource,var5);

        //资源树根节点
        List<Tree<ResourceView>> rootList = new ArrayList<>();

        Iterator<Tree<ResourceView>> var8 = allResourceTree.iterator();
        while (var8.hasNext()) {
            Tree<ResourceView> resourceTree = var8.next();
            if (StringUtils.equals(resourceTree.getAttribute().getParentId(), Constant.ROOT)) {
                rootList.add(resourceTree);
            }
        }
        rootList.stream().forEach(root -> this.getChildren(root, allResourceTree));
        return rootList;
    }

    /**
     * 找到菜单子节点并设置进去
     *
     * @param root
     * @param var2
     */
    private void getChildren(Tree<ResourceView> root, List<Tree<ResourceView>> var2) {
        Iterator<Tree<ResourceView>> var3 = var2.iterator();
        Tree<ResourceView> tree;
        List<Tree<ResourceView>> childrenTree = new ArrayList<>();
        while (var3.hasNext()) {
            tree = var3.next();
            if (StringUtils.equals(root.getId(), tree.getAttribute().getParentId())) {
                childrenTree.add(tree);
                root.setChildren(childrenTree);
                var3.remove();
            }
        }
        if (!childrenTree.isEmpty()) {
            childrenTree.stream().forEach(childTree -> this.getChildren(childTree, var2));
        }
    }

    /**
     * 获得用户用户或者角色管理的资源
     * @param userId
     * @param roleId
     * @return
     */
    private Map<String,String> getSubjectResource(String userId,String roleId){
        List<UserMenuView> var3;
        if (!StringUtils.isBlank(roleId)){
            var3 = userPrivilegeMapper.selectRoleResources(roleId);
        }else {
            if (StringUtils.isBlank(userId)){
                userId = UserManager.getCurrentUserId();
            }
            var3 = userPrivilegeMapper.selectUserMenu(userId);
        }
        if (null == var3 && var3.isEmpty()){
            return new HashMap<>();
        }
        return var3.stream().collect(Collectors.toMap(UserMenuView::getPkResourceId,UserMenuView::getResourceName));
    }

    /**
     * 将资源集合转换成树形集合对象
     * @param var1 资源集合
     * @param var2 key为与角色或者用户管理的主键id
     * @return
     */
    private List<Tree<ResourceView>> getResourceTreeView(List<ResourceView> var1, Map<String,String> var2){
        List<Tree<ResourceView>> allTree = new ArrayList<>();
        //查询到当前登录用户
        Tree<ResourceView> tree;
        ResourceView resourceView;
        Iterator<ResourceView> var6 = var1.iterator();
        while (var6.hasNext()) {
            tree = new Tree<>();
            resourceView = var6.next();
            if (var2.containsKey(resourceView.getPkResourceId())){
                tree.setChecked(true);
            }
            tree.setId(resourceView.getPkResourceId());
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
        if (StringUtils.isEmpty(resourceId)) {
            throw new ServerException("资源id为空！");
        }
        Resource resource = resourceMapper.selectById(resourceId);
        if (resource == null) {
            return new Resource();
        }
        return resource;
    }

    /**
     * 编辑资源时验证
     *
     * @param editResourceForm
     */
    private void editResourceValidate(EditResourceForm editResourceForm) {
        if (StringUtils.isEmpty(editResourceForm.getPkResourceId())) {//新增验证
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isEmpty(editResourceForm.getResourceName())) {
                sb.append("资源名称为空！");
            }
            if (StringUtils.isEmpty(editResourceForm.getResourceUrl())) {
                sb.append("资源URL为空！");
            }
            if (null == editResourceForm.getResourceType()) {
                sb.append("资源类型为空！");
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                logger.info("新增资源失败,原因是：" + sb.toString());
                throw new ServerException(sb.toString());
            }
            checkExist("resource_name", editResourceForm.getResourceName(), 1);
            checkExist("resource_url", editResourceForm.getResourceUrl(), 1);
            if (StringUtils.isNotEmpty(editResourceForm.getPerms())) {
                checkExist("perms", editResourceForm.getPerms(), 1);
            }
        } else {
            Resource resource = resourceMapper.selectById(editResourceForm.getPkResourceId());
            if (resource == null) {
                throw new ServerException("资源id无效!");
            }
            if (StringUtils.isNotEmpty(editResourceForm.getResourceName())) {
                checkExist("resource_name", editResourceForm.getResourceName(), 2);
            }
            if (StringUtils.isNotEmpty(editResourceForm.getResourceUrl())) {
                checkExist("resource_url", editResourceForm.getResourceUrl(), 2);
            }
            if (StringUtils.isNotEmpty(editResourceForm.getPerms())) {
                checkExist("perms", editResourceForm.getPerms(), 2);
            }
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
        List<Resource> resourceList = resourceMapper.selectList(new EntityWrapper<Resource>()
                .eq(column, params)
                .eq("status", CodeEnum.ENABLE.getCode())
        );
        if (1 == addOrUpdate) {
            if (!resourceList.isEmpty()) {
                throw new ServerException("已经存在相同的" + params);
            }
        } else if (2 == addOrUpdate) {
            if (Constant.ONE < resourceList.size()) {
                throw new ServerException("已经存在相同的" + params);
            }
        }

    }

    /**
     * 新增的资源必须与超级管理员关联
     *
     * @param resourceId
     */
    @Transactional
    private void addResourceRoleRel(String resourceId, int resourceType) {
        List<Role> roleList = roleMapper.selectList(new EntityWrapper<Role>()
                .eq("role_name", Constant.SuperAdmin)
        );
        if (!roleList.isEmpty()) {
            String pkRoleResourceId = UUIDUtils.getPkUUID();
            String userId = UserManager.getCurrentUserId();
            Date curDate = DateUtils.getCurrentDate();
            String roleId = roleList.get(Constant.ZERO).getPkRoleId();
            RoleResourceRel roleResourceRel = new RoleResourceRel();
            roleResourceRel.setPkRoleResourceId(pkRoleResourceId);
            roleResourceRel.setRoleId(roleId);
            roleResourceRel.setResourceId(resourceId);
            roleResourceRel.setResourceType(resourceType);
            roleResourceRel.setCreateTime(curDate);
            roleResourceRel.setCreateUserId(userId);
            int count;
            count = roleResourceRelMapper.insert(roleResourceRel);
            if (count == Constant.ZERO) {
                throw new ServerException("单个新增角色资源关联个数为 0 条！");
            }
        }
    }

    /**
     * 获取资源序号
     *
     * @param editResourceForm
     * @return
     */
    private int getResourceNo(EditResourceForm editResourceForm) {
        EntityWrapper wrapper = new EntityWrapper<Resource>();
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
    @Transactional
    private void updateParent(String parentId) {
        List<Resource> resourceList = resourceMapper.selectList(new EntityWrapper<Resource>()
                .eq("parent_id", parentId)
        );
        if (!resourceList.isEmpty()) {
            if (false == resourceList.get(Constant.ZERO).getIsParent()) {
                resourceList.get(Constant.ZERO).setIsParent(Boolean.TRUE);
                resourceMapper.updateById(resourceList.get(Constant.ZERO));
            }

        }
    }
}
