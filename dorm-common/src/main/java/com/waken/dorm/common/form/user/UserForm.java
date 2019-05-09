package com.waken.dorm.common.form.user;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName UserForm
 * @Description UserForm
 * @Author zhaoRong
 * @Date 2019/3/21 20:59
 **/
@ToString
@Getter
@Setter
public class UserForm extends BaseForm {
    private static final long serialVersionUID = -5388397198183714365L;
    private String userId;

    private String mobile;

    private String userName;

    private String password;

    private String name;

    private Integer gender;

    private String nickName;

    private Integer userType;

    private Integer status;

    private String email;

    private String createUserName;

    private String lastModifyUserName;
}
