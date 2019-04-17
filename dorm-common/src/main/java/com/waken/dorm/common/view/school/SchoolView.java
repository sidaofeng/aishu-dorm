package com.waken.dorm.common.view.school;

import java.util.Date;

/**
 * @ClassName SchoolView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 19:40
 **/
public class SchoolView {
    private String pkSchoolId;

    private String userName;

    private String mobile;

    private String email;

    private String schoolName;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    public String getPkSchoolId() {
        return pkSchoolId;
    }

    public void setPkSchoolId(String pkSchoolId) {
        this.pkSchoolId = pkSchoolId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "SchoolView{" +
                "pkSchoolId='" + pkSchoolId + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
