package com.waken.dorm.common.form.dorm;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AddDormRepairForm
 * @Description 新增维修记录form表单
 * @Author zhaoRong
 * @Date 2019/4/1 20:57
 **/
public class AddDormRepairForm {
    private String dormNum;

    private Integer repairType;

    private String repairDesc;

    private String repairImgUrl;

    private Integer studentNum;

    private String studentMobile;

    private String memo;

    private String studentToken;

    private String studentId;

    private Integer terminal;

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentToken() {
        return studentToken;
    }

    public void setStudentToken(String studentToken) {
        this.studentToken = studentToken;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public Integer getRepairType() {
        return repairType;
    }

    public void setRepairType(Integer repairType) {
        this.repairType = repairType;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public String getRepairImgUrl() {
        return repairImgUrl;
    }

    public void setRepairImgUrl(String repairImgUrl) {
        this.repairImgUrl = repairImgUrl;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AddDormRepairForm{" +
                "dormNum='" + dormNum + '\'' +
                ", repairType=" + repairType +
                ", repairDesc='" + repairDesc + '\'' +
                ", repairImgUrl='" + repairImgUrl + '\'' +
                ", studentNum=" + studentNum +
                ", studentMobile='" + studentMobile + '\'' +
                ", memo='" + memo + '\'' +
                ", studentToken='" + studentToken + '\'' +
                ", studentId='" + studentId + '\'' +
                ", terminal=" + terminal +
                '}';
    }
}
