package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName DormBuildingForm
 * @Description DormBuildingForm
 * @Author zhaoRong
 * @Date 2019/3/31 12:21
 **/
public class DormBuildingForm extends BaseForm {
    private static final long serialVersionUID = -3547493633581949862L;
    private String pkDormBuildingId;

    private String schoolId;

    private String schoolName;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    public String getPkDormBuildingId() {
        return pkDormBuildingId;
    }

    public void setPkDormBuildingId(String pkDormBuildingId) {
        this.pkDormBuildingId = pkDormBuildingId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getDormBuildingType() {
        return dormBuildingType;
    }

    public void setDormBuildingType(Integer dormBuildingType) {
        this.dormBuildingType = dormBuildingType;
    }

    public String getDormBuildingNum() {
        return dormBuildingNum;
    }

    public void setDormBuildingNum(String dormBuildingNum) {
        this.dormBuildingNum = dormBuildingNum;
    }

    public Integer getDormBuildingLevels() {
        return dormBuildingLevels;
    }

    public void setDormBuildingLevels(Integer dormBuildingLevels) {
        this.dormBuildingLevels = dormBuildingLevels;
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
        return "DormBuildingForm{" +
                "pkDormBuildingId='" + pkDormBuildingId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", dormBuildingType=" + dormBuildingType +
                ", dormBuildingNum='" + dormBuildingNum + '\'' +
                ", dormBuildingLevels=" + dormBuildingLevels +
                ", status=" + status +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                '}';
    }
}
