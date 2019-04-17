package com.waken.dorm.common.form.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseForm
 * @Description BaseForm
 * @Author zhaoRong
 * @Date 2019/3/25 13:45
 **/
public class BaseForm implements Serializable {
    private static final long serialVersionUID = -4679926683630991034L;
    private Integer pageNum = 1;

    private Integer pageSize = 20;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BaseForm{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
