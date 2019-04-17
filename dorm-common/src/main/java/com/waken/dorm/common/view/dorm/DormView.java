package com.waken.dorm.common.view.dorm;

import java.util.Date;

/**
 * @ClassName DormView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 12:19
 **/
public class DormView {
    private String pkDormId;

    private String dormBuildingNum;

    private Integer buildingLevelth;

    private Integer dormType;

    private String dormNum;

    private String dormDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    public String getPkDormId() {
        return pkDormId;
    }

    public void setPkDormId(String pkDormId) {
        this.pkDormId = pkDormId;
    }

    public String getDormBuildingNum() {
        return dormBuildingNum;
    }

    public void setDormBuildingNum(String dormBuildingNum) {
        this.dormBuildingNum = dormBuildingNum;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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
}
