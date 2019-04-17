package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import java.util.Date;
/**
 * @ClassName DormBuilding
 * @Description 宿舍楼实体类
 * @Author zhaoRong
 * @Date 2019/3/31 10:48
 **/
public class DormBuilding extends BaseEntity {
    private static final long serialVersionUID = 8862025902942253471L;
    private String pkDormBuildingId;

    private String schoolId;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;

    public String getPkDormBuildingId() {
        return pkDormBuildingId;
    }

    public void setPkDormBuildingId(String pkDormBuildingId) {
        this.pkDormBuildingId = pkDormBuildingId == null ? null : pkDormBuildingId.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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
        this.dormBuildingNum = dormBuildingNum == null ? null : dormBuildingNum.trim();
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
        this.dormBuildingDesc = dormBuildingDesc == null ? null : dormBuildingDesc.trim();
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