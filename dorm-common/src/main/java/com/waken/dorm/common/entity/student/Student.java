package com.waken.dorm.common.entity.student;

import com.waken.dorm.common.annotation.ExcelColumn;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName Student
 * @Description Student
 * @Author zhaoRong
 * @Date 2019/3/29 21:10
 **/
@Getter
@Setter
public class Student extends BaseEntity {
    private static final long serialVersionUID = 7634334849749799694L;
    private String pkStudentId;

    private String schoolId;

    @ExcelColumn(name = "姓名", column = "A")
    private String studentName;

    @ExcelColumn(name = "学号", column = "B")
    private Integer studentNum;

    private String password;

    @ExcelColumn(name = "联系方式", column = "C")
    private String mobile;

    private Integer gender;

    private String email;

    private String imgUrl;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}