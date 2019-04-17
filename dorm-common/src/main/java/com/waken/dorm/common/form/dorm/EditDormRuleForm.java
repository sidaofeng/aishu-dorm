package com.waken.dorm.common.form.dorm;

import java.util.Date;

/**
 * @ClassName EditDormRuleForm
 * @Description EditDormRuleForm
 * @Author zhaoRong
 * @Date 2019/4/2 10:35
 **/
public class EditDormRuleForm {
    private String pkDormRuleId;

    private String ruleName;

    private String ruleDesc;

    private Integer status;

    private String memo;

    private String schoolId;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPkDormRuleId() {
        return pkDormRuleId;
    }

    public void setPkDormRuleId(String pkDormRuleId) {
        this.pkDormRuleId = pkDormRuleId;
    }

    public String getRuleName() {
        return ruleName;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "EditDormRuleForm{" +
                "pkDormRuleId='" + pkDormRuleId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
