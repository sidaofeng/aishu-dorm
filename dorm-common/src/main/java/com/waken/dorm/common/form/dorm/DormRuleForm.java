package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName DormRuleForm
 * @Description DormRuleForm
 * @Author zhaoRong
 * @Date 2019/4/2 10:38
 **/
public class DormRuleForm extends BaseForm {
    private static final long serialVersionUID = 3080831935512618337L;
    private String pkDormRuleId;

    private String schoolId;

    private String schoolName;

    private String ruleName;

    private String ruleDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    private String studentId;

    private Integer terminal;

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getPkDormRuleId() {
        return pkDormRuleId;
    }

    public void setPkDormRuleId(String pkDormRuleId) {
        this.pkDormRuleId = pkDormRuleId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "DormRuleForm{" +
                "pkDormRuleId='" + pkDormRuleId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUserName='" + createUserName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", memo='" + memo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", terminal=" + terminal +
                '}';
    }
}
