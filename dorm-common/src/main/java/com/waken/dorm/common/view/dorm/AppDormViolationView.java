package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName AppDormViolationView
 * @Description AppDormViolationView
 * @Author zhaoRong
 * @Date 2019/4/3 11:39
 **/
public class AppDormViolationView {
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String memo;

    public String getPkDormViolationId() {
        return pkDormViolationId;
    }

    public void setPkDormViolationId(String pkDormViolationId) {
        this.pkDormViolationId = pkDormViolationId;
    }

    public String getDormRuleName() {
        return dormRuleName;
    }

    public void setDormRuleName(String dormRuleName) {
        this.dormRuleName = dormRuleName;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getViolationImgUrl() {
        return violationImgUrl;
    }

    public void setViolationImgUrl(String violationImgUrl) {
        this.violationImgUrl = violationImgUrl;
    }

    public String getViolationReason() {
        return violationReason;
    }

    public void setViolationReason(String violationReason) {
        this.violationReason = violationReason;
    }

    public String getSolveResult() {
        return solveResult;
    }

    public void setSolveResult(String solveResult) {
        this.solveResult = solveResult;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
