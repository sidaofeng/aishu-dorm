package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-05
 */
@Getter
@Setter
@ToString
@TableName("rm_dorm_repair")
public class DormRepair extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 宿舍id
     */
    @TableField("dorm_id")
    private String dormId;

    /**
     * 维修类型(门窗、床、水电)
     */
    @TableField("repair_type")
    private Integer repairType;

    /**
     * 维修描述
     */
    @TableField("repair_desc")
    private String repairDesc;

    /**
     * 维修图片
     */
    @TableField("repair_img_url")
    private String repairImgUrl;

    /**
     * 报修人
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 报修人联系方式
     */
    @TableField("student_mobile")
    private String studentMobile;

    /**
     * 状态（0：未维修1：已维修2：已报废）
     */
    private Integer status;

    /**
     * 维修费用
     */
    @TableField("repair_cost")
    private BigDecimal repairCost;

    /**
     * 维修费用发票
     */
    @TableField("repair_bill_url")
    private String repairBillUrl;

    /**
     * 备注
     */
    private String memo;
}
