package com.waken.dorm.serviceImpl.resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.entity.resource.ResourceExample;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleExample;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.role.RoleResourceRelExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.form.resource.ResourceForm;
import com.waken.dorm.common.form.resource.ResourceMenuForm;
import com.waken.dorm.common.form.resource.ResourceTreeForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.common.view.resource.ResourceTreeView;
import com.waken.dorm.common.view.resource.ResourceView;
import com.waken.dorm.dao.resource.ResourceMapper;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.role.RoleResourceRelMapper;
import com.waken.dorm.service.resource.ResourceService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    /**
     * 新增或修改资源
     *
     * @param editResourceForm
     * @return
     */
    @Transactional
    @Override
    public Resource saveResource(EditResourceForm editResourceForm) {
        this.editSourceValidate(editResourceForm);
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count = Constant.ZERO;
        Resource resource = new Resource();
        BeanMapper.copy(editResourceForm,resource);
        resource.setLastModifyTime(curDate);
        resource.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editResourceForm.getPkResourceId())){//新增
            logger.info("service: 新增资源开始");
            String pkResourceId = UUIDUtils.getPkUUID();
            int resourceNo = Constant.ZERO;

            if (StringUtils.isEmpty(editResourceForm.getParentId())){
                resource.setIsParent(CodeEnum.YES.getCode());
            }else {
                resource.setIsParent(CodeEnum.NO.getCode());
                resource.setParentId(editResourceForm.getParentId());
                this.updateParent(editResourceForm.getParentId());
            }
            resourceNo = this.getResourceNo(editResourceForm);
            resource.setResourceNo(resourceNo);
            if (editResourceForm.getResourceType() == null){
                resource.setResourceType(CodeEnum.MENU.getCode());
            }else {
                resource.setResourceType(editResourceForm.getResourceType());
            }
            resource.setPkResourceId(pkResourceId);
            resource.setStatus(CodeEnum.ENABLE.getCode());
            resource.setCreateTime(curDate);
            resource.setCreateUserId(userId);
            count = resourceMapper.insertSelective(resource);
            if (count == Constant.ZERO){
                throw new DormException("新增资源失败");
            }
            //新增的资源必须与超级管理员关联
            this.addResourceRoleRel(pkResourceId);
            return resource;
        }else {//修改
            logger.info("service: 更新资源开始");
            resourceMapper.updateByPrimaryKeySelective(resource);
            return resourceMapper.selectByPrimaryKey(editResourceForm.getPkResourceId());
        }

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
        int count = Constant.ZERO;
        List<String> delResourceList = this.recursionGetDelIds(resourceIds);
        Set<String> delIdsSet = new HashSet<>(delResourceList);// 去掉重复的id
        delResourceIds.clear();
        delResourceList.addAll(delIdsSet);
        logger.info("需要删除的资源id: "+delResourceList.toString());
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            // 删除资源与角色的关联
            List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectByResourceIds(delResourceList);
            if (!roleResourceRelList.isEmpty()){
                StringBuffer sb = new StringBuffer();
                for (RoleResourceRel roleResourceRel : roleResourceRelList){
                    if (CodeEnum.ENABLE.getCode() == roleResourceRel.getStatus()){
                        sb.append(roleResourceRel.getResourceId());
                    }
                }
                if (StringUtils.isEmpty(sb.toString())){//删除资源与角色关联
                    RoleResourceRelExample example = new RoleResourceRelExample();
                    RoleResourceRelExample.Criteria criteria = example.createCriteria();
                    criteria.andResourceIdIn(delResourceList);
                    roleResourceRelMapper.deleteByExample(example);
                }else {
                    throw new DormException("删除资源失败，原因是以下资源与角色关联生效中："+sb.toString());
                }
            }
            // 删除资源本身
            ResourceExample resourceExample = new ResourceExample();
            ResourceExample.Criteria resourceCriteria = resourceExample.createCriteria();
            resourceCriteria.andPkResourceIdIn(delResourceList);
            count = resourceMapper.deleteByExample(resourceExample);
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else if(CodeEnum.NO.getCode() == delStatus){
            count = resourceMapper.batchUpdateStatus(getToUpdateStatusMap(delResourceList,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
            RoleResourceRelExample example = new RoleResourceRelExample();
            RoleResourceRelExample.Criteria criteria = example.createCriteria();
            criteria.andResourceIdIn(resourceIds);
            roleResourceRelMapper.deleteByExample(example);
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询资源
     *
     * @param resourceForm
     * @return
     */
    @Override
    public PageInfo<Resource> listResourcePages(ResourceForm resourceForm) {
        logger.info("service: 分页查询资源开始");
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(resourceForm.getPkResourceId())){
            criteria.andPkResourceIdEqualTo(resourceForm.getPkResourceId());
        }
        if(StringUtils.isNotEmpty(resourceForm.getResourceName())){
            criteria.andResourceNameLike(resourceForm.getResourceName());
        }
        if(resourceForm.getStatus()!=null){
            criteria.andStatusEqualTo(resourceForm.getStatus());
        }
        if(resourceForm.getResourceNo()!=null){
            criteria.andResourceNoEqualTo(resourceForm.getResourceNo());
        }
        if(resourceForm.getResourceType()!=null){
            criteria.andResourceTypeEqualTo(resourceForm.getResourceType());
        }
        if(!StringUtils.isEmpty(resourceForm.getParentId())){
            criteria.andParentIdEqualTo(resourceForm.getParentId());
        }
        example.setOrderByClause("last_modify_time desc");
        PageHelper.startPage(resourceForm.getPageNum(),resourceForm.getPageSize());
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        return new PageInfo<Resource>(resourceList);
    }

    /**
     * 查询用户登陆后拥有的菜单资源集合
     *
     * @param resourceMenuForm
     * @return
     */
    @Override
    public List<ResourceMenuView> listMenuResources(ResourceMenuForm resourceMenuForm) {
        logger.info("service: 查询当前用户拥有的菜单资源开始");
        resourceMenuForm.setStatus(CodeEnum.ENABLE.getCode());
        if (StringUtils.isEmpty(resourceMenuForm.getUserId())){
            String userId = ShiroUtils.getUserId();
            logger.info("当前登陆用户是："+userId);
            resourceMenuForm.setUserId(userId);
        }
        List<ResourceMenuView> resourceTreeViews = resourceMapper.listAllResourcesByMenuForm(resourceMenuForm);
        if (resourceTreeViews.isEmpty()){
            return new ArrayList<>();
        }
        return resourceTreeViews;
    }

    /**
     * 查询所有的资源树
     *
     * @return
     */
    @Override
    public List<ResourceMenuView> listAllResources(ResourceMenuForm resourceMenuForm) {
        logger.info("service: 查询所有的资源树开始");
        List<ResourceMenuView> resourceTreeViewList = resourceMapper.listAllResources();
        String userId = ShiroUtils.getUserId();
        resourceMenuForm.setStatus(CodeEnum.ENABLE.getCode());
        List<ResourceMenuView> resourceMenuViews = resourceMapper.listAllResourcesByMenuForm(resourceMenuForm);
        StringBuffer resourceIds = new StringBuffer();
        if(!resourceMenuViews.isEmpty()){
            for(ResourceMenuView resourceMenuView : resourceMenuViews){
                resourceIds.append(resourceMenuView.getPkResourceId()).append(",");
            }
        }
        if (!resourceTreeViewList.isEmpty()){
            for(ResourceMenuView resourceMenuView : resourceTreeViewList){
                if(resourceIds.toString().contains(resourceMenuView.getPkResourceId())){
                    resourceMenuView.setSelected(true);
                    resourceMenuView.setOpen(true);
                    resourceMenuView.setzAsync(true);
                }
            }
        }
        return resourceTreeViewList;
    }

    /**
     * 获取用于shiro所有的资源
     *
     * @return
     */
    @Override
    public List<ResourceMenuView> listAllResourcesForShiro() {
        logger.info("service: 获取用于shiro所有的资源开始");
        List<ResourceMenuView> resourceTreeViewList = resourceMapper.listAllResources();
        if (resourceTreeViewList.isEmpty()){
            return new ArrayList<>();
        }
        return resourceTreeViewList;
    }

    /**
     * 查询资源树集合
     *
     * @param resourceTreeForm
     * @return
     */
    @Override
    public List<TreeView> listResourceTree(ResourceTreeForm resourceTreeForm) {
        logger.info("service: 开始进入资源树查询");
        ResourceMenuForm resourceMenuForm = new ResourceMenuForm();
        if (StringUtils.isNotEmpty(resourceTreeForm.getPkResourceId())){
            resourceMenuForm.setPkResourceId(resourceTreeForm.getPkResourceId());
        }
        String userId = ShiroUtils.getUserId();
        resourceMenuForm.setUserId(userId);
        resourceMenuForm.setStatus(CodeEnum.YES.getCode());
        List<ResourceMenuView> resources = resourceMapper.listAllResourcesByMenuForm(resourceMenuForm);
        List<TreeView> treeViews = new ArrayList<>();
        for (ResourceMenuView resource : resources){
            TreeView treeView = new TreeView();
            if (StringUtils.isEmpty(resource.getParentId())){
                treeView.setPkId(resource.getPkResourceId());
                treeView.setText(resource.getResourceName());
                List<TreeView> nodes = this.getNodes(resource.getPkResourceId(),userId);
                treeView.setNodes(nodes);
                treeViews.add(treeView);
            }
        }
        return treeViews;
    }

    /**
     * 通过资源id查询资源
     *
     * @param resourceTreeForm
     * @return
     */
    @Override
    public ResourceView queryResourceByPkId(ResourceTreeForm resourceTreeForm) {
        if (StringUtils.isEmpty(resourceTreeForm.getPkResourceId())){
            throw new DormException("资源id为空！");
        }
        ResourceView resourceView = resourceMapper.queryResourceByPkId(resourceTreeForm.getPkResourceId());
        if (resourceView == null){
            return new ResourceView();
        }
        return resourceView;
    }

    /**
     * 递归得到子节点
     * @param resourceId
     * @return
     */
    private List<TreeView> getNodes(String resourceId,String userId){
        ResourceMenuForm resourceMenuForm = new ResourceMenuForm();
        resourceMenuForm.setUserId(userId);
        resourceMenuForm.setParentId(resourceId);
        resourceMenuForm.setStatus(CodeEnum.YES.getCode());
        List<ResourceMenuView> resources = resourceMapper.listAllResourcesByMenuForm(resourceMenuForm);
        if (resources.isEmpty()){
            return new ArrayList<TreeView>();
        }else {
            List<TreeView> treeViews = new ArrayList<>();
            for (ResourceMenuView resource : resources){
                TreeView treeView = new TreeView();
                treeView.setPkId(resource.getPkResourceId());
                treeView.setText(resource.getResourceName());
                List<TreeView> nodes = this.getNodes(resource.getPkResourceId(),userId);
                treeView.setNodes(nodes);
                treeViews.add(treeView);
            }
            return treeViews;
        }
    }

    /**
     * 编辑资源时验证
     * @param editResourceForm
     */
    private void editSourceValidate(EditResourceForm editResourceForm){
        if (StringUtils.isEmpty(editResourceForm.getPkResourceId())){//新增验证
            StringBuffer sb = new StringBuffer();
            if(StringUtils.isEmpty(editResourceForm.getResourceName())){
                sb.append("资源名称为空！");
            }
            if(StringUtils.isEmpty(editResourceForm.getResourceUrl())){
                sb.append("资源URL为空！");
            }
            if(StringUtils.isNotEmpty(sb.toString())){
                logger.info("新增资源失败,原因是：" + sb.toString());
                throw new DormException(sb.toString());
            }
            ResourceExample example = new ResourceExample();
            ResourceExample.Criteria criteria = example.createCriteria();
            criteria.andResourceNameEqualTo(editResourceForm.getResourceName());
            ResourceExample.Criteria orCriteria = example.createCriteria();
            orCriteria.andResourceUrlEqualTo(editResourceForm.getResourceUrl());
            example.or(orCriteria);
            List<Resource> resourceList = resourceMapper.selectByExample(example);
            if (!resourceList.isEmpty()){
                throw new DormException("已经存在相同名称或url的资源！");
            }
        }else {
            Resource resource = resourceMapper.selectByPrimaryKey(editResourceForm.getPkResourceId());
            if (resource == null){
                throw new DormException("资源id无效!");
            }
            ResourceExample example = new ResourceExample();
            ResourceExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotEmpty(editResourceForm.getResourceName())){
                criteria.andResourceNameEqualTo(editResourceForm.getResourceName());
            }
            ResourceExample.Criteria orCriteria = example.createCriteria();
            if (StringUtils.isNotEmpty(editResourceForm.getResourceName())){
                orCriteria.andResourceUrlEqualTo(editResourceForm.getResourceUrl());
            }
            example.or(orCriteria);
            List<Resource> resourceList = resourceMapper.selectByExample(example);
            if (Constant.ONE < resourceList.size()){
                throw new DormException("已经存在相同名称或url的资源！");
            }
        }

    }

    /**
     * 新增的资源必须与超级管理员关联
     * @param resourceId
     */
    @Transactional
    private void addResourceRoleRel(String resourceId){
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(Constant.SuperAdmin);
        List<Role> roleList = roleMapper.selectByExample(example);
        if (!roleList.isEmpty()){
            String pkRoleResourceId = UUIDUtils.getPkUUID();
            String userId = ShiroUtils.getUserId();
            Date curDate = DateUtils.getCurrentDate();
            String roleId = roleList.get(Constant.ZERO).getPkRoleId();
            RoleResourceRel roleResourceRel = new RoleResourceRel();
            roleResourceRel.setPkRoleResourceId(pkRoleResourceId);
            roleResourceRel.setRoleId(roleId);
            roleResourceRel.setResourceId(resourceId);
            roleResourceRel.setStatus(CodeEnum.ENABLE.getCode());
            roleResourceRel.setCreateTime(curDate);
            roleResourceRel.setCreateUserId(userId);
            roleResourceRel.setLastModifyTime(curDate);
            roleResourceRel.setLastModifyUserId(userId);
            int count = Constant.ZERO;
            count = roleResourceRelMapper.insertSelective(roleResourceRel);
            if (count == Constant.ZERO){
                throw new DormException("单个新增角色资源关联个数为 0 条！");
            }
        }
    }

    /**
     * 获取资源序号
     * @param editResourceForm
     * @return
     */
    private int getResourceNo(EditResourceForm editResourceForm){
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(editResourceForm.getParentId())){
            criteria.andParentIdIsNull();
        }else {
            criteria.andParentIdEqualTo(editResourceForm.getParentId());
        }
        criteria.andStatusEqualTo(CodeEnum.ENABLE.getCode());
        return resourceMapper.countByExample(example);
    }

    /**
     * 得到所有的子资源
     * @param resourceIds
     * @return
     */

    /**
     * 递归获取子资源
     * @param resourceId
     * @return
     */
    private static List<String> delResourceIds = new ArrayList<>();// 接收子节点
    private List<String> recursionGetDelIds(List<String> resourceIds){
        delResourceIds.addAll(resourceIds);
        for(String sourceId : resourceIds){
            List<String> childIds = this.getChildIdsByParentId(sourceId);
            if (!childIds.isEmpty()){
                this.recursionGetDelIds(childIds);
            }
        }
        return delResourceIds;
    }

    /**
     * 通过父级id获取该父级的id的子节点
     * @param parentId
     * @return
     */
    private List<String> getChildIdsByParentId(String parentId){
        List<String> resourceIds = new ArrayList<>();
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        if (!resourceList.isEmpty()){
            for (Resource resource : resourceList){
                resourceIds.add(resource.getPkResourceId());
            }
        }
        return resourceIds;
    }

    /**
     * 更新父级（isprarent）字段
     * @param parentId
     */
    @Transactional
    private void updateParent(String parentId){
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        criteria.andPkResourceIdEqualTo(parentId);
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        if (!resourceList.isEmpty()){
            if (CodeEnum.NO.getCode() == resourceList.get(Constant.ZERO).getIsParent()){
                resourceList.get(Constant.ZERO).setIsParent(CodeEnum.YES.getCode());
                resourceMapper.updateByPrimaryKeySelective(resourceList.get(Constant.ZERO));
            }

        }
    }
}
