package com.waken.dorm.common.view.dorm;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DormRepairView
 * @Description DormRepairView
 * @Author zhaoRong
 * @Date 2019/4/3 14:08
 **/
public class DormRepairView {
    private String pkDormRepairId;

    private String dormNum;

    private Integer repairType;

    private String repairDesc;

    private String repairImgUrl;

    private String studentName;

    private String studentMobile;

    private Integer status;

    private BigDecimal repairCost;

    private String repairBillUrl;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    public String getPkDormRepairId() {
        return pkDormRepairId;
    }

    public void setPkDormRepairId(String pkDormRepairId) {
        this.pkDormRepairId = pkDormRepairId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public String getRepairBillUrl() {
        return repairBillUrl;
    }

    public void setRepairBillUrl(String repairBillUrl) {
        this.repairBillUrl = repairBillUrl;
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
