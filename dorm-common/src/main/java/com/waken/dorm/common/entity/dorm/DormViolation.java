package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import java.util.Date;

public class DormViolation extends BaseEntity {
    private static final long serialVersionUID = 4524755969231835492L;
    private String pkDormViolationId;

    private String dormRuleId;

    private String dormId;

    private String studentId;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;

    public String getPkDormViolationId() {
        return pkDormViolationId;
    }

    public void setPkDormViolationId(String pkDormViolationId) {
        this.pkDormViolationId = pkDormViolationId == null ? null : pkDormViolationId.trim();
    }

    public String getDormRuleId() {
        return dormRuleId;
    }

    public void setDormRuleId(String dormRuleId) {
        this.dormRuleId = dormRuleId == null ? null : dormRuleId.trim();
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId == null ? null : dormId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getViolationImgUrl() {
        return violationImgUrl;
    }

    public void setViolationImgUrl(String violationImgUrl) {
        this.violationImgUrl = violationImgUrl == null ? null : violationImgUrl.trim();
    }

    public String getViolationReason() {
        return violationReason;
    }

    public void setViolationReason(String violationReason) {
        this.violationReason = violationReason == null ? null : violationReason.trim();
    }

    public String getSolveResult() {
        return solveResult;
    }

    public void setSolveResult(String solveResult) {
        this.solveResult = solveResult == null ? null : solveResult.trim();
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