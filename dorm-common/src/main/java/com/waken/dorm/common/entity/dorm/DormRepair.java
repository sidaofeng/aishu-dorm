package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class DormRepair extends BaseEntity {
    private static final long serialVersionUID = 2908626138039550759L;
    private String pkDormRepairId;

    private String dormId;

    private Integer repairType;

    private String repairDesc;

    private String repairImgUrl;

    private String studentId;

    private String studentMobile;

    private Integer status;

    private BigDecimal repairCost;

    private String repairBillUrl;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}