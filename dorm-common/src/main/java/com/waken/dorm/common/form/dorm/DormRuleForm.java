package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DormRuleForm
 * @Description DormRuleForm
 * @Author zhaoRong
 * @Date 2019/4/2 10:38
 **/
@ToString
@Getter
@Setter
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
}
