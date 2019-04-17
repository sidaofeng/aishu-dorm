package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class DormRepair extends BaseEntity {
    private static final long serialVersionUID = 2908626138039550759L;
    private String pkDormRepairId;

    private String dormId;

    private Integer repairType;

    private String repairDesc;

    private String repairImgUrl;

    private String studentId;

    private String studentMobile;

    private Integer status;

    private BigDecimal repairCost;

    private String repairBillUrl;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;

    public String getPkDormRepairId() {
        return pkDormRepairId;
    }

    public void setPkDormRepairId(String pkDormRepairId) {
        this.pkDormRepairId = pkDormRepairId == null ? null : pkDormRepairId.trim();
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId == null ? null : dormId.trim();
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
        this.repairDesc = repairDesc == null ? null : repairDesc.trim();
    }

    public String getRepairImgUrl() {
        return repairImgUrl;
    }

    public void setRepairImgUrl(String repairImgUrl) {
        this.repairImgUrl = repairImgUrl == null ? null : repairImgUrl.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile == null ? null : studentMobile.trim();
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
        this.repairBillUrl = repairBillUrl == null ? null : repairBillUrl.trim();
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