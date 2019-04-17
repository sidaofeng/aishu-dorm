package com.waken.dorm.common.form.role;

import com.waken.dorm.common.form.base.BaseForm;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * @ClassName QueryRoleForm
 * @Description 查询角色信息的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 13:49
 **/
@ApiModel(value = "QueryRoleForm", description = "查询角色信息的form 表单")
public class QueryRoleForm extends BaseForm {
    private static final long serialVersionUID = -3442287827988689012L;
    private String pkRoleId;

    private String roleName;

    private String roleDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    public String getPkRoleId() {
        return pkRoleId;
    }

    public void setPkRoleId(String pkRoleId) {
        this.pkRoleId = pkRoleId == null ? null : pkRoleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    @Override
    public String toString() {
        return "QueryRoleForm{" +
                "pkRoleId='" + pkRoleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserId='" + lastModifyUserId + '\'' +
                '}';
    }
}
