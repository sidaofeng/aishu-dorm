package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName DormForm
 * @Description DormForm
 * @Author zhaoRong
 * @Date 2019/3/31 13:11
 **/
public class DormForm extends BaseForm {
    private static final long serialVersionUID = 5300270031224198511L;
    private String pkDormId;

    private String dormBuildingId;

    private Integer buildingLevelth;

    private Integer dormType;

    private String dormNum;

    private String dormDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;

    private String schoolId;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "DormForm{" +
                "pkDormId='" + pkDormId + '\'' +
                ", dormBuildingId='" + dormBuildingId + '\'' +
                ", buildingLevelth=" + buildingLevelth +
                ", dormType=" + dormType +
                ", dormNum='" + dormNum + '\'' +
                ", dormDesc='" + dormDesc + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserId='" + lastModifyUserId + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
