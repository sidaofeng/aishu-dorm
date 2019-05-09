package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DormRepairForm
 * @Description DormRepairForm
 * @Author zhaoRong
 * @Date 2019/4/1 21:02
 **/
@ToString
@Getter
@Setter
public class DormRepairForm extends BaseForm {
    private static final long serialVersionUID = -3982339641414695388L;
    private String pkDormRepairId;

    private String dormId;

    private Integer repairType;

    private String repairDesc;

    private String studentName;

    private String studentId;

    private String studentMobile;

    private Integer status;

    private BigDecimal repairCost;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    private Integer terminal;
}
