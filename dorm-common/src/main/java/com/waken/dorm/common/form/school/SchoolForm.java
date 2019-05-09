package com.waken.dorm.common.form.school;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName SchoolForm
 * @Description SchoolForm
 * @Author zhaoRong
 * @Date 2019/4/3 19:30
 **/
@ToString
@Getter
@Setter
public class SchoolForm extends BaseForm {
    private static final long serialVersionUID = 5745529911914184908L;
    private String pkSchoolId;

    private String userName;

    private String schoolName;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String memo;
}
