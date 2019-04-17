package com.waken.dorm.common.base;

import java.util.Date;

/**
 * @ClassName UpdateStatusEntity
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 11:09
 **/
public class UpdateStatusEntity extends BaseEntity{
    private String pkId;

    private Integer status;

    private Date lastModifyTime;

    private String lastModifyUserId;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }
}
