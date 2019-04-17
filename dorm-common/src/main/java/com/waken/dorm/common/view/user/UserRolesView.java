package com.waken.dorm.common.view.user;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName UserRolesView
 * @Description 与用户已经存在关联的角色，没有关联的角色的视图
 * @Author zhaoRong
 * @Date 2019/3/25 18:30
 **/
@ApiModel(value = "UserRolesView", description = "与用户已经存在关联的角色，没有关联的角色的视图")
public class UserRolesView {
    private List<UserRoleRelView> addedList;//与用户已经存在关联的角色

    private List<UserRoleRelView> toBeAddList;//没有关联的角色

    public List<UserRoleRelView> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<UserRoleRelView> addedList) {
        this.addedList = addedList;
    }

    public List<UserRoleRelView> getToBeAddList() {
        return toBeAddList;
    }

    public void setToBeAddList(List<UserRoleRelView> toBeAddList) {
        this.toBeAddList = toBeAddList;
    }
}
