package com.waken.dorm.common.form.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName QueryUserForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/5 19:10
 **/
@ToString
@Getter
@Setter
public class QueryUserForm {
    private String userName;

    private String password;
}
