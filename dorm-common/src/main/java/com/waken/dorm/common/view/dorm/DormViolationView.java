package com.waken.dorm.common.view.dorm;

import java.util.Date;

/**
 * @ClassName DormViolationView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 9:41
 **/
public class DormViolationView {
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

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
