package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import java.util.Date;

public class Dorm extends BaseEntity {
    private static final long serialVersionUID = -7939742015609393104L;
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

    public String getPkDormId() {
        return pkDormId;
    }

    public void setPkDormId(String pkDormId) {
        this.pkDormId = pkDormId == null ? null : pkDormId.trim();
    }

    public String getDormBuildingId() {
        return dormBuildingId;
    }

    public void setDormBuildingId(String dormBuildingId) {
        this.dormBuildingId = dormBuildingId == null ? null : dormBuildingId.trim();
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
        this.dormNum = dormNum == null ? null : dormNum.trim();
    }

    public String getDormDesc() {
        return dormDesc;
    }

    public void setDormDesc(String dormDesc) {
        this.dormDesc = dormDesc == null ? null : dormDesc.trim();
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
        this.createUserId = createUserId == null ? null : createUserId.trim();
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}