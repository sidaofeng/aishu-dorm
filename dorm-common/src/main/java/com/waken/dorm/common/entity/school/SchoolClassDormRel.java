package com.waken.dorm.common.entity.school;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SchoolClassDormRel extends BaseEntity {
    private static final long serialVersionUID = -8794705007422190889L;
    private String pkSchoolClassDormId;

    private String schoolClassId;

    private String dormId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}