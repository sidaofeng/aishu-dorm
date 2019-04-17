package com.waken.dorm.common.view.user;

/**
 * @ClassName UserRoleRelView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/25 18:33
 **/

public class UserRoleRelView {
    private String pkUserRoleId;

    private String roleId;

    private String roleName;

    private String roleDesc;

    public String getPkUserRoleId() {
        return pkUserRoleId;
    }

    public void setPkUserRoleId(String pkUserRoleId) {
        this.pkUserRoleId = pkUserRoleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
}
