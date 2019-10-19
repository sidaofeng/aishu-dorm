package com.waken.dorm.common.form.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditUserForm
 * @Description 编辑用户form表单
 * @Author zhaoRong
 * @Date 2019/4/3 10:54
 **/
@ToString
@Getter
@Setter
public class EditUserForm {
    private String userId;

    private String userName;

    private String name;

    private String mobile;

    private String email;

    private Integer gender;

    private Integer userType;
}
