package com.waken.dorm.common.entity.log;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SysLog extends BaseEntity {
    private String pkLogId;

    private String userId;

    private String operationContent;

    private Integer duration;

    private String method;

    private String params;

    private String ip;

    private Date createTime;

    private String location;
}