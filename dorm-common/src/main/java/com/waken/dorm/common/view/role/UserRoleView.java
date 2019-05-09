package com.waken.dorm.common.view.role;

import com.waken.dorm.common.enums.CodeEnum;
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
public class UserRoleView {
    private String roleId;

    private String roleName;

    private Integer isSelect = CodeEnum.NO.getCode();//角色与用户存在关联表示选中
}
