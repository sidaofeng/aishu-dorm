package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName ListDormScoreForm
 * @Description ListDormScoreForm
 * @Author zhaoRong
 * @Date 2019/3/31 19:36
 **/
public class ListDormScoreForm extends BaseForm {
    private static final long serialVersionUID = -223148826994314229L;
    private String pkDormScoreId;

    private String dormNum;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    private String studentId;

    public String getPkDormScoreId() {
        return pkDormScoreId;
    }

    public void setPkDormScoreId(String pkDormScoreId) {
        this.pkDormScoreId = pkDormScoreId;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ListDormScoreForm{" +
                "pkDormScoreId='" + pkDormScoreId + '\'' +
                ", dormNum='" + dormNum + '\'' +
                ", status=" + status +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", memo='" + memo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
