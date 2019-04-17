package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DormRepairForm
 * @Description DormRepairForm
 * @Author zhaoRong
 * @Date 2019/4/1 21:02
 **/
public class DormRepairForm extends BaseForm {
    private static final long serialVersionUID = -3982339641414695388L;
    private String pkDormRepairId;

    private String dormId;

    private Integer repairType;

    private String repairDesc;

    private String studentName;

    private String studentId;

    private String studentMobile;

    private Integer status;

    private BigDecimal repairCost;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    private Integer terminal;

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPkDormRepairId() {
        return pkDormRepairId;
    }

    public void setPkDormRepairId(String pkDormRepairId) {
        this.pkDormRepairId = pkDormRepairId;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return "DormRepairForm{" +
                "pkDormRepairId='" + pkDormRepairId + '\'' +
                ", dormId='" + dormId + '\'' +
                ", repairType=" + repairType +
                ", repairDesc='" + repairDesc + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentMobile='" + studentMobile + '\'' +
                ", status=" + status +
                ", repairCost=" + repairCost +
                ", createTime=" + createTime +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", memo='" + memo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
