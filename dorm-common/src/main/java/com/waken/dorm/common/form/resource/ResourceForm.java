package com.waken.dorm.common.form.resource;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName ResourceForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
public class ResourceForm extends BaseForm {
    private static final long serialVersionUID = -5910404465143943089L;
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private Integer resourceNo;

    private String parentId;

    private Integer status;

    private Integer isParent;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    @Override
    public String toString() {
        return "ResourceForm{" +
                "pkResourceId='" + pkResourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType=" + resourceType +
                ", resourceNo=" + resourceNo +
                ", parentId='" + parentId + '\'' +
                ", status=" + status +
                ", isParent=" + isParent +
                '}';
    }
}
