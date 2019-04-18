package com.waken.dorm.common.form.student;

import com.waken.dorm.common.form.base.BaseForm;

/**
 * @ClassName StudentForm
 * @Description StudentForm
 * @Author zhaoRong
 * @Date 2019/3/29 21:48
 **/
public class StudentForm extends BaseForm {
    private static final long serialVersionUID = -8526720785286340960L;
    private String pkStudentId;

    private String schoolId;

    private String schoolClassId;

    private String pkDormBuildingId;

    private String dormId;

    private String dormBuildingNum;

    private String dormNum;

    private String studentName;

    private String schoolName;

    private String schoolClassName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    public String getPkStudentId() {
        return pkStudentId;
    }

    public void setPkStudentId(String pkStudentId) {
        this.pkStudentId = pkStudentId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public String getPkDormBuildingId() {
        return pkDormBuildingId;
    }

    public void setPkDormBuildingId(String pkDormBuildingId) {
        this.pkDormBuildingId = pkDormBuildingId;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getDormBuildingNum() {
        return dormBuildingNum;
    }

    public void setDormBuildingNum(String dormBuildingNum) {
        this.dormBuildingNum = dormBuildingNum;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }

    public void setSchoolClassName(String schoolClassName) {
        this.schoolClassName = schoolClassName;
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

    @Override
    public String toString() {
        return "StudentForm{" +
                "pkStudentId='" + pkStudentId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolClassId='" + schoolClassId + '\'' +
                ", pkDormBuildingId='" + pkDormBuildingId + '\'' +
                ", dormId='" + dormId + '\'' +
                ", dormBuildingNum='" + dormBuildingNum + '\'' +
                ", dormNum='" + dormNum + '\'' +
                ", studentName='" + studentName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolClassName='" + schoolClassName + '\'' +
                ", studentNum=" + studentNum +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                '}';
    }
}
