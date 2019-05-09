package com.waken.dorm.common.entity.school;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SchoolClassStudentRel extends BaseEntity {
    private static final long serialVersionUID = 2661060967792227717L;
    private String pkSchoolClassStudentId;

    private String schoolClassId;

    private String studentId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}