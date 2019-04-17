package com.waken.dorm.common.form.user;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName AddUserRoleRelForm
 * @Description 新增用户与角色关联的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 18:16
 **/
@ApiModel(value = "AddUserRoleRelForm", description = "新增用户与角色关联的form 表单")
public class AddUserRoleRelForm {
    private String userId;

    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "AddUserRoleRelForm{" +
                "userId='" + userId + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}
