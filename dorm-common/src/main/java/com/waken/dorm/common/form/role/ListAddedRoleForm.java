package com.waken.dorm.common.form.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ListAddedRoleForm
 * @Description 查询用户角色信息 form 表单
 * @Author zhaoRong
 * @Date 2019/3/26 10:02
 **/
@ToString
@Getter
@Setter
public class ListAddedRoleForm {
    private String userId;

    private String curUserId;//当前登录的用户
}
