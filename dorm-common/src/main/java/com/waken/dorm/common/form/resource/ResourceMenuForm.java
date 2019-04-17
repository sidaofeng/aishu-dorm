package com.waken.dorm.common.form.resource;

/**
 * @ClassName ResourceMenuForm
 * @Description ResourceMenuForm
 * @Author zhaoRong
 * @Date 2019/4/5 22:04
 **/
public class ResourceMenuForm {
    private String pkResourceId;

    private String userId;

    private String parentId;

    private String roleId;

    private Integer resourceType;

    private Integer status;

    public String getPkResourceId() {
        return pkResourceId;
    }

    public void setPkResourceId(String pkResourceId) {
        this.pkResourceId = pkResourceId;
    }

    public String getUserId() {
        return userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResourceMenuForm{" +
                "pkResourceId='" + pkResourceId + '\'' +
                ", userId='" + userId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", resourceType=" + resourceType +
                ", status=" + status +
                '}';
    }
}
