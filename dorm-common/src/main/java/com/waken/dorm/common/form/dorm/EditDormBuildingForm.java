package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

/**
 * @ClassName EditDormBuildingForm
 * @Description 编辑宿舍楼信息form表单
 * @Author zhaoRong
 * @Date 2019/3/31 10:48
 **/
public class EditDormBuildingForm {
    private String pkDormBuildingId;

    private String schoolId;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private String memo;

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

    public String getDormBuildingDesc() {
        return dormBuildingDesc;
    }

    public void setDormBuildingDesc(String dormBuildingDesc) {
        this.dormBuildingDesc = dormBuildingDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "EditDormBuildingForm{" +
                "pkDormBuildingId='" + pkDormBuildingId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", dormBuildingType=" + dormBuildingType +
                ", dormBuildingNum='" + dormBuildingNum + '\'' +
                ", dormBuildingLevels=" + dormBuildingLevels +
                ", dormBuildingDesc='" + dormBuildingDesc + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                '}';
    }
}
