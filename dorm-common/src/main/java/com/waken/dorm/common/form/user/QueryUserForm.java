package com.waken.dorm.common.form.user;

/**
 * @ClassName QueryUserForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/5 19:10
 **/
public class QueryUserForm {
    private String userName;

    private String password;

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

    @Override
    public String toString() {
        return "QueryUserForm{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
