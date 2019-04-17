package com.waken.dorm.service.resource;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.form.resource.ResourceForm;
import com.waken.dorm.common.form.resource.ResourceMenuForm;
import com.waken.dorm.common.form.resource.ResourceTreeForm;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.common.view.resource.ResourceTreeView;
import com.waken.dorm.common.view.resource.ResourceView;

import java.util.List;

/**
 * @ClassName ResourceService
 * @Description ResourceService
 * @Author zhaoRong
 * @Date 2019/3/21 19:48
 **/
public interface ResourceService {
    /**
     * 新增或修改资源
     * @param editResourceForm
     * @return
     */
    public Resource saveResource(EditResourceForm editResourceForm);
    /**
     *删除资源
     */
    public void deleteResource(DeleteForm deleteForm);

    /**
     * 分页查询资源
     * @param resourceForm
     * @return
     */
    public PageInfo<Resource> listResourcePages(ResourceForm resourceForm);
    /**
     * 查询用户登陆后拥有的菜单资源集合
     * @param resourceMenuForm
     * @return
     */
    public List<ResourceMenuView> listMenuResources(ResourceMenuForm resourceMenuForm);

    /**
     * 查询所有的资源菜单
     * @return
     */
    public List<ResourceMenuView> listAllResources(ResourceMenuForm resourceMenuForm);

    /**
     * 获取用于shiro所有的资源
     * @return
     */
    public List<ResourceMenuView> listAllResourcesForShiro();

    /**
     * 查询资源树集合
     * @param resourceTreeForm
     * @return
     */
    public List<TreeView> listResourceTree(ResourceTreeForm resourceTreeForm);

    /**
     * 通过资源id查询资源
     * @param resourceTreeForm
     * @return
     */
    public ResourceView queryResourceByPkId(ResourceTreeForm resourceTreeForm);
}
