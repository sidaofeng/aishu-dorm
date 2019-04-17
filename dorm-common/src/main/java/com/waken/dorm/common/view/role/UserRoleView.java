package com.waken.dorm.common.view.role;

import com.waken.dorm.common.enums.CodeEnum;

/**
 * @ClassName UserRoleView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 16:04
 **/
public class UserRoleView {
    private String roleId;

    private String roleName;

    private Integer isSelect = CodeEnum.NO.getCode();//角色与用户存在关联表示选中

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

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }
}
