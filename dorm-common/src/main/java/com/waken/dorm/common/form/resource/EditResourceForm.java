package com.waken.dorm.common.form.resource;

import java.util.Date;

/**
 * @ClassName EditResourceForm
 * @Description EditResourceForm
 * @Author zhaoRong
 * @Date 2019/4/4 14:08
 **/
public class EditResourceForm {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

    private String parentId;

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

    @Override
    public String toString() {
        return "EditResourceForm{" +
                "pkResourceId='" + pkResourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType=" + resourceType +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceNo=" + resourceNo +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
