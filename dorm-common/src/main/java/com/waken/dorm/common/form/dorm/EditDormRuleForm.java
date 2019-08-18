package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditDormRuleForm
 * @Description EditDormRuleForm
 * @Author zhaoRong
 * @Date 2019/4/2 10:35
 **/
@ToString
@Getter
@Setter
public class EditDormRuleForm {
    private String pkDormRuleId;

    private String ruleName;

    private String ruleDesc;

    private Integer status;

    private String memo;
}
