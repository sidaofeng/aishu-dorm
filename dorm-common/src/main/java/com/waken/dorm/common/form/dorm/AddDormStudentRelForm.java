package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName AddDormStudentRelForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/31 14:12
 **/
@ToString
@Getter
@Setter
public class AddDormStudentRelForm {
    private String pkDormId; //需要绑定的宿舍id

    private List<String> pkStudentIds; //需要绑定的学生 id 集合
}
