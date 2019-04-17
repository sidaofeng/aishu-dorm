package com.waken.dorm.common.form.school;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName SchoolForm
 * @Description SchoolForm
 * @Author zhaoRong
 * @Date 2019/4/3 19:30
 **/
public class SchoolForm extends BaseForm {
    private static final long serialVersionUID = 5745529911914184908L;
    private String pkSchoolId;

    private String userName;

    private String schoolName;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

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
        return "SchoolForm{" +
                "pkSchoolId='" + pkSchoolId + '\'' +
                ", userName='" + userName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", status=" + status +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", memo='" + memo + '\'' +
                '}';
    }
}
