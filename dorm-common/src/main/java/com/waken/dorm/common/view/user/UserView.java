package com.waken.dorm.common.view.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName UserView
 * @Description UserView
 * @Author zhaoRong
 * @Date 2019/4/3 8:55
 **/
@Getter
@Setter
public class UserView {
    private String userId;

    private String roleName;

    private String mobile;

    private String userName;

    private Integer gender;

    private String name;

    private Integer status;

    private String imgUrl;

    private String email;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
