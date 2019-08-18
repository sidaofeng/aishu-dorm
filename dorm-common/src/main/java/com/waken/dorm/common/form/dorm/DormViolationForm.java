package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName DormViolationForm
 * @Description DormViolationForm
 * @Author zhaoRong
 * @Date 2019/4/2 12:55
 **/
@ToString
@Getter
@Setter
public class DormViolationForm extends BaseForm {
    private static final long serialVersionUID = 1451135143242343587L;
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    private String memo;

    private String studentId;
}
