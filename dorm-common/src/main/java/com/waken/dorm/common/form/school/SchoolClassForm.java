package com.waken.dorm.common.form.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName SchoolClassForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/4 23:22
 **/
@ToString
@Getter
@Setter
public class SchoolClassForm{
    private String pkSchoolClassId;

    private String schoolId;

    private String className;

    private Integer status;
}
