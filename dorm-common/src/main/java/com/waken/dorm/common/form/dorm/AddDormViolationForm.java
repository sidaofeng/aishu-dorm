package com.waken.dorm.common.form.dorm;

import java.util.Date;

/**
 * @ClassName AddDormViolationForm
 * @Description AddDormViolationForm
 * @Author zhaoRong
 * @Date 2019/4/2 12:50
 **/
public class AddDormViolationForm {
    private String dormRuleId;

    private String dormNum;

    private Integer studentNum;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private String memo;

    public String getDormRuleId() {
        return dormRuleId;
    }

    public void setDormRuleId(String dormRuleId) {
        this.dormRuleId = dormRuleId;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AddDormViolationForm{" +
                "dormRuleId='" + dormRuleId + '\'' +
                ", dormNum='" + dormNum + '\'' +
                ", studentNum=" + studentNum +
                ", violationImgUrl='" + violationImgUrl + '\'' +
                ", violationReason='" + violationReason + '\'' +
                ", solveResult='" + solveResult + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                '}';
    }
}
