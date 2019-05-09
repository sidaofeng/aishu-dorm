package com.waken.dorm.common.form.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName SchoolClassTreeForm
 * @Description SchoolClassTreeForm
 * @Author zhaoRong
 * @Date 2019/4/5 23:24
 **/
@ToString
@Getter
@Setter
public class SchoolClassTreeForm {

    private String pkSchoolClassId;

    private String parentId;

    private String schoolId;
}
