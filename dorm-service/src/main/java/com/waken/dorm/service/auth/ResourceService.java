package com.waken.dorm.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.ButtonResources;
import com.waken.dorm.common.form.resource.EditButtonsForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.ResourceView;

import java.util.List;

/**
 * @ClassName ResourceService
 * @Description ResourceService
 * @Author zhaoRong
 * @Date 2019/3/21 19:48
 **/
public interface ResourceService  extends IService<Resource> {
    /**
     * 新增或修改资源
     *
     * @param editResourceForm
     * @return
     */
    Resource saveResource(EditResourceForm editResourceForm);

    /**
     * 删除资源
     * @param deleteForm
     */
    void deleteResource(DeleteForm deleteForm);

    /**
     * 查询资源树集合
     *
     * @param userId
     * @param roleId
     * @return
     */
    List<Tree<ResourceView>> getResourcesTree(String userId, String roleId);

    /**
     * 通过资源id查询资源
     *
     * @param resourceId
     * @return
     */
    Resource selectById(String resourceId);

    /**
     * 通过资源id查询对应的按钮权限
     * @param resourceId
     * @return
     */
    List<ButtonResources> selectButtonByResourceId(String resourceId);

    /**
     * 批量保存按钮资源
     * @param editButtonsForm
     * @return
     */
    int batchSaveButton(EditButtonsForm editButtonsForm);
}
