package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DormRule extends BaseEntity {
    private static final long serialVersionUID = -1941896431805819460L;
    private String pkDormRuleId;

    private String schoolId;

    private String ruleName;

    private String ruleDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}