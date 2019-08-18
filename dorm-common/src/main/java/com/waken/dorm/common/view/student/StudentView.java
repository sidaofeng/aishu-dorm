package com.waken.dorm.common.view.student;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName StudentView
 * @Description StudentView
 * @Author zhaoRong
 * @Date 2019/4/4 16:17
 **/
@Getter
@Setter
public class StudentView extends BaseView {
    private String pkStudentId;

    private String studentName;

    private Integer studentNum;

    private String schoolName;

    private String schoolClassName;

    private String dormBuildingNum;

    private String dormNum;

    private String mobile;

    private Integer gender;

    private String email;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;
}
