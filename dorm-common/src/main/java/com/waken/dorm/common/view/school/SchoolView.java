package com.waken.dorm.common.view.school;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName SchoolView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 19:40
 **/
@ToString
@Getter
@Setter
public class SchoolView extends BaseView {
    private String pkSchoolId;

    private String userName;

    private String mobile;

    private String email;

    private String schoolName;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
