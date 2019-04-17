package com.waken.dorm.common.form.role;

import java.util.Date;

/**
 * @ClassName EditRoleForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 11:12
 **/
public class EditRoleForm {
    private String pkRoleId;

    private String roleName;

    private String roleDesc;

    public String getPkRoleId() {
        return pkRoleId;
    }

    public void setPkRoleId(String pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "EditRoleForm{" +
                "pkRoleId='" + pkRoleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
