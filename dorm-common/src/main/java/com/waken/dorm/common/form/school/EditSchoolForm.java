package com.waken.dorm.common.form.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName EditSchoolForm
 * @Description EditSchoolForm
 * @Author zhaoRong
 * @Date 2019/4/3 19:24
 **/
@ToString
@Getter
@Setter
public class EditSchoolForm {
    private String pkSchoolId;

    private String userName;

    private String password;

    private String mobile;

    private String email;

    private String schoolName;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String memo;
}
