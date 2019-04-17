package com.waken.dorm.common.view.resource;

/**
 * @ClassName ResourceView
 * @Description ResourceView
 * @Author zhaoRong
 * @Date 2019/4/6 22:27
 **/
public class ResourceView {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

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
}
