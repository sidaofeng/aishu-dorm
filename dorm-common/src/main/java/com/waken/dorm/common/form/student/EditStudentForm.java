package com.waken.dorm.common.form.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditStudentForm
 * @Description EditStudentForm
 * @Author zhaoRong
 * @Date 2019/4/4 16:03
 **/
@ToString
@Getter
@Setter
public class EditStudentForm {
    private String pkStudentId;

    private String studentName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private String imgUrl;
}
