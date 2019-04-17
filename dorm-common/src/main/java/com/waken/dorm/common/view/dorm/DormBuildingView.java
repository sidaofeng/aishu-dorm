package com.waken.dorm.common.view.dorm;

import java.util.Date;

/**
 * @ClassName DormBuildingView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 14:54
 **/
public class DormBuildingView {
    private String pkDormBuildingId;

    private String schoolName;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    public String getPkDormBuildingId() {
        return pkDormBuildingId;
    }

    public void setPkDormBuildingId(String pkDormBuildingId) {
        this.pkDormBuildingId = pkDormBuildingId;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
