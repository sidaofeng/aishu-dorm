package com.waken.dorm.common.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName UpdateStatusEntity
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 11:09
 **/
@Getter
@Setter
public class UpdateStatusEntity extends BaseEntity {
    private String pkId;

    private Integer status;

    private Date lastModifyTime;

    private String lastModifyUserId;

    public String getPkId() {
        return pkId;
    }
}
