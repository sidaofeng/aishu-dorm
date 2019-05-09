package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DormViolation extends BaseEntity {
    private static final long serialVersionUID = 4524755969231835492L;
    private String pkDormViolationId;

    private String dormRuleId;

    private String dormId;

    private String studentId;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}