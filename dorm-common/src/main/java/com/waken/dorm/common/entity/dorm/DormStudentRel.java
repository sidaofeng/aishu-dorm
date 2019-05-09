package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DormStudentRel extends BaseEntity {
    private static final long serialVersionUID = -8623452440474879954L;
    private String pkDormStudentId;

    private String dormId;

    private String studentId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}