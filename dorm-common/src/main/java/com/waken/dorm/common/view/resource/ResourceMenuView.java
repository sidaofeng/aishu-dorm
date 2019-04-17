package com.waken.dorm.common.view.resource;

import io.swagger.annotations.ApiModel;

/**
 * @ClassName ResourceMenuView
 * @Description 资源菜单视图
 * @Author zhaoRong
 * @Date 2019/4/5 22:03
 **/
@ApiModel(value = "ResourceMenuView", description = "资源菜单视图")
public class ResourceMenuView {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

    private String parentId;

    private Integer isParent;

    private boolean open;

    private boolean zAsync;

    private boolean isSelected;

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

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Integer getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(Integer resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean iszAsync() {
        return zAsync;
    }

    public void setzAsync(boolean zAsync) {
        this.zAsync = zAsync;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
