package com.waken.dorm.common.form.dorm;

/**
 * @ClassName EditDormForm
 * @Description EditDormForm
 * @Author zhaoRong
 * @Date 2019/3/31 13:11
 **/
public class EditDormForm {
    private String pkDormId;

    private String dormBuildingId;

    private Integer buildingLevelth;

    private Integer dormType;

    private String dormNum;

    private String dormDesc;

    private Integer status;

    private String memo;

    public String getPkDormId() {
        return pkDormId;
    }

    public void setPkDormId(String pkDormId) {
        this.pkDormId = pkDormId;
    }

    public String getDormBuildingId() {
        return dormBuildingId;
    }

    public void setDormBuildingId(String dormBuildingId) {
        this.dormBuildingId = dormBuildingId;
    }

    public Integer getBuildingLevelth() {
        return buildingLevelth;
    }

    public void setBuildingLevelth(Integer buildingLevelth) {
        this.buildingLevelth = buildingLevelth;
    }

    public Integer getDormType() {
        return dormType;
    }

    public void setDormType(Integer dormType) {
        this.dormType = dormType;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public String getDormDesc() {
        return dormDesc;
    }

    public void setDormDesc(String dormDesc) {
        this.dormDesc = dormDesc;
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
        return "EditDormForm{" +
                "pkDormId='" + pkDormId + '\'' +
                ", dormBuildingId='" + dormBuildingId + '\'' +
                ", buildingLevelth=" + buildingLevelth +
                ", dormType=" + dormType +
                ", dormNum='" + dormNum + '\'' +
                ", dormDesc='" + dormDesc + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                '}';
    }
}
