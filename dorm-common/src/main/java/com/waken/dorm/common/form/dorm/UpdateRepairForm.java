package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @ClassName UpdateRepairForm
 * @Description 更新维修表form表单
 * @Author zhaoRong
 * @Date 2019/4/1 21:09
 **/
@ToString
@Getter
@Setter
public class UpdateRepairForm {

    private String id;

    private Integer status;

    private BigDecimal repairCost;

    private String repairBillUrl;

    private String memo;
}
