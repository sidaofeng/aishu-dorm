package com.waken.dorm.common.entity.school;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SchoolUserRel extends BaseEntity {
    private static final long serialVersionUID = 7696560429338481261L;
    private String pkSchoolUserId;

    private String schoolId;

    private String userId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}