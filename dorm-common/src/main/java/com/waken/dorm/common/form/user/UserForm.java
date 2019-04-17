package com.waken.dorm.common.form.user;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName UserForm
 * @Description UserForm
 * @Author zhaoRong
 * @Date 2019/3/21 20:59
 **/
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", nickName='" + nickName + '\'' +
                ", userType=" + userType +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                '}';
    }
}
