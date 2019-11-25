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
    private String id;

    private String name;

    private String description;

    private Integer status;

    private String memo;
}
