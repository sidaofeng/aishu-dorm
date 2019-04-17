package com.waken.dorm.common.form.student;

/**
 * @ClassName EditStudentForm
 * @Description EditStudentForm
 * @Author zhaoRong
 * @Date 2019/4/4 16:03
 **/
public class EditStudentForm {
    private String pkStudentId;

    private String studentName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private String imgUrl;

    public String getPkStudentId() {
        return pkStudentId;
    }

    public void setPkStudentId(String pkStudentId) {
        this.pkStudentId = pkStudentId;
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

    @Override
    public String toString() {
        return "EditStudentForm{" +
                "pkStudentId='" + pkStudentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentNum=" + studentNum +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
