package com.waken.dorm.common.form.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName EditSchoolClassForm
 * @Description EditSchoolClassForm
 * @Author zhaoRong
 * @Date 2019/4/1 18:34
 **/
@ToString
@Getter
@Setter
public class EditSchoolClassForm {
    private String pkSchoolClassId;

    private String schoolId;

    private String parentId;

    private String className;

    private String classDesc;

    private String memo;
}
