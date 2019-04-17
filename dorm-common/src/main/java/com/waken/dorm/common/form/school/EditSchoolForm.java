package com.waken.dorm.common.form.school;

import java.util.Date;

/**
 * @ClassName EditSchoolForm
 * @Description EditSchoolForm
 * @Author zhaoRong
 * @Date 2019/4/3 19:24
 **/
public class EditSchoolForm {
    private String pkSchoolId;

    private String userName;

    private String password;

    private String mobile;

    private String email;

    private String schoolName;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "EditSchoolForm{" +
                "pkSchoolId='" + pkSchoolId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", memo='" + memo + '\'' +
                '}';
    }
}
