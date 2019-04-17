package com.waken.dorm.common.entity.student;

import com.waken.dorm.common.base.BaseEntity;

import java.util.Date;

/**
 * @ClassName StudentInfo
 * @Description StudentInfo
 * @Author zhaoRong
 * @Date 2019/3/30 14:02
 **/
public class StudentInfo extends BaseEntity {
    private static final long serialVersionUID = -6872689417624493481L;
    private String studentId;

    private String studentName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private String imgUrl;

    private Date createTime;

    private String studentToken;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStudentToken() {
        return studentToken;
    }

    public void setStudentToken(String studentToken) {
        this.studentToken = studentToken;
    }
}
