package com.waken.dorm.common.view.user;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserRoleRelView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/25 18:33
 **/
@Getter
@Setter
public class UserRoleRelView extends BaseView {
    private String pkUserRoleId;

    private String roleId;

    private String roleName;

    private String roleDesc;
}
