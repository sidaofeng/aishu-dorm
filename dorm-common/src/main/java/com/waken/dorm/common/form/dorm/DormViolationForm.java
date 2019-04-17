package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName DormViolationForm
 * @Description DormViolationForm
 * @Author zhaoRong
 * @Date 2019/4/2 12:55
 **/
public class DormViolationForm extends BaseForm {
    private static final long serialVersionUID = 1451135143242343587L;
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPkDormViolationId() {
        return pkDormViolationId;
    }

    public void setPkDormViolationId(String pkDormViolationId) {
        this.pkDormViolationId = pkDormViolationId;
    }

    public String getDormRuleName() {
        return dormRuleName;
    }

    public void setDormRuleName(String dormRuleName) {
        this.dormRuleName = dormRuleName;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return "DormViolationForm{" +
                "pkDormViolationId='" + pkDormViolationId + '\'' +
                ", dormRuleName='" + dormRuleName + '\'' +
                ", dormNum='" + dormNum + '\'' +
                ", studentName='" + studentName + '\'' +
                ", status=" + status +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", memo='" + memo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
