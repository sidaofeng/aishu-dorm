package com.waken.dorm.common.form.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditRoleForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 11:12
 **/
@ToString
@Getter
@Setter
public class EditRoleForm {
    private String pkRoleId;

    private String roleName;

    private String roleDesc;
}
