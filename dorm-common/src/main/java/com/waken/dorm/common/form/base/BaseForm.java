package com.waken.dorm.common.form.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseForm
 * @Description BaseForm
 * @Author zhaoRong
 * @Date 2019/3/25 13:45
 **/
@Getter
@Setter
@ToString
public class BaseForm implements Serializable {
    private static final long serialVersionUID = -4679926683630991034L;
    private Integer pageNum = 1;

    private Integer pageSize = 20;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;
}
