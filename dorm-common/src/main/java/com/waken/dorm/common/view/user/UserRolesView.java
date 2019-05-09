package com.waken.dorm.common.view.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName UserRolesView
 * @Description 与用户已经存在关联的角色，没有关联的角色的视图
 * @Author zhaoRong
 * @Date 2019/3/25 18:30
 **/
@ApiModel(value = "UserRolesView", description = "与用户已经存在关联的角色，没有关联的角色的视图")
@Getter
@Setter
public class UserRolesView {
    private List<UserRoleRelView> addedList;//与用户已经存在关联的角色

    private List<UserRoleRelView> toBeAddList;//没有关联的角色
}
