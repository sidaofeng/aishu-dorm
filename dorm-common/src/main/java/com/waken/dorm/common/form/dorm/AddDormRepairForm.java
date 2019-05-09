package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AddDormRepairForm
 * @Description 新增维修记录form表单
 * @Author zhaoRong
 * @Date 2019/4/1 20:57
 **/
@ToString
@Getter
@Setter
public class AddDormRepairForm {
    private String dormNum;

    private Integer repairType;

    private String repairDesc;

    private String repairImgUrl;

    private Integer studentNum;

    private String studentMobile;

    private String memo;

    private String studentToken;

    private String studentId;

    private Integer terminal;
}
