package com.waken.dorm.common.view.resource;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ResourceTreeView
 * @Description 资源树视图
 * @Author zhaoRong
 * @Date 2019/4/5 22:14
 **/
@ApiModel(value = "ResourceTreeView", description = "资源树视图")
public class ResourceTreeView {
    private String pkResourceId;

    private String resourceName;

    private List<ResourceTreeView> childResourceTreeView = new ArrayList<ResourceTreeView>();

    public String getPkResourceId() {
        return pkResourceId;
    }

    public void setPkResourceId(String pkResourceId) {
        this.pkResourceId = pkResourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<ResourceTreeView> getChildResourceTreeView() {
        return childResourceTreeView;
    }

    public void setChildResourceTreeView(List<ResourceTreeView> childResourceTreeView) {
        this.childResourceTreeView = childResourceTreeView;
    }
}
