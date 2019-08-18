package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName AddDormViolationForm
 * @Description AddDormViolationForm
 * @Author zhaoRong
 * @Date 2019/4/2 12:50
 **/
@ToString
@Getter
@Setter
public class AddDormViolationForm {
    private String dormRuleId;

    private String dormNum;

    private Integer studentNum;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private String memo;
}
