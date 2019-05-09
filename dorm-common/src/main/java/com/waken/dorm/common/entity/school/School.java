package com.waken.dorm.common.entity.school;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class School extends BaseEntity {
    private static final long serialVersionUID = -1960828549936687156L;
    private String pkSchoolId;

    private String schoolName;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String memo;
}