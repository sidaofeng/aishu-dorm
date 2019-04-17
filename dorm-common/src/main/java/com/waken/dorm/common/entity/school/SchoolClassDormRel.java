package com.waken.dorm.common.entity.school;

import com.waken.dorm.common.base.BaseEntity;
import java.util.Date;

public class SchoolClassDormRel extends BaseEntity {
    private static final long serialVersionUID = -8794705007422190889L;
    private String pkSchoolClassDormId;

    private String schoolClassId;

    private String dormId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;

    public String getPkSchoolClassDormId() {
        return pkSchoolClassDormId;
    }

    public void setPkSchoolClassDormId(String pkSchoolClassDormId) {
        this.pkSchoolClassDormId = pkSchoolClassDormId == null ? null : pkSchoolClassDormId.trim();
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId == null ? null : schoolClassId.trim();
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId == null ? null : dormId.trim();
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