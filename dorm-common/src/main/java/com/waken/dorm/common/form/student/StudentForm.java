package com.waken.dorm.common.form.student;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName StudentForm
 * @Description StudentForm
 * @Author zhaoRong
 * @Date 2019/3/29 21:48
 **/
@ToString
@Getter
@Setter
public class StudentForm extends BaseForm {
    private static final long serialVersionUID = -8526720785286340960L;
    private String pkStudentId;

    private String schoolId;

    private String schoolClassId;

    private String pkDormBuildingId;

    private String dormId;

    private String dormBuildingNum;

    private String dormNum;

    private String studentName;

    private String schoolName;

    private String schoolClassName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;
}
