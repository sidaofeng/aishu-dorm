package com.waken.dorm.common.view.log;

import java.util.Date;

/**
 * @ClassName SysLogView
 * @Description SysLogView
 * @Author zhaoRong
 * @Date 2019/4/16 13:01
 **/
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

    public String getPkLogId() {
        return pkLogId;
    }

    public void setPkLogId(String pkLogId) {
        this.pkLogId = pkLogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
