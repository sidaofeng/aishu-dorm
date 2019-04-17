package com.waken.dorm.common.form.role;

/**
 * @ClassName ListAddedRoleForm
 * @Description 查询用户角色信息 form 表单
 * @Author zhaoRong
 * @Date 2019/3/26 10:02
 **/
public class ListAddedRoleForm {
    private String userId;

    private String curUserId;//当前登录的用户

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(String curUserId) {
        this.curUserId = curUserId;
    }

    @Override
    public String toString() {
        return "ListAddedRoleForm{" +
                "userId='" + userId + '\'' +
                ", curUserId='" + curUserId + '\'' +
                '}';
    }
}
