package com.waken.dorm.common.view.role;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserRoleView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 16:04
 **/
@Getter
@Setter
public class UserRoleView extends BaseView {
    private String id;

    private String name;

    private boolean isSelect = false;//角色与用户存在关联表示选中
}
