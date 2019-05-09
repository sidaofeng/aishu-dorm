package com.waken.dorm.common.view.log;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName SysLogView
 * @Description SysLogView
 * @Author zhaoRong
 * @Date 2019/4/16 13:01
 **/
@Getter
@Setter
public class SysLogView {
    private String pkLogId;

    private String userName;

    private String operationContent;

    private Integer duration;

    private String method;

    private String params;

    private String ip;

    private Date createTime;

    private String location;
}
