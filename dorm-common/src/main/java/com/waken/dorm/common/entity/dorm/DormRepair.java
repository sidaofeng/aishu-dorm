package com.waken.dorm.common.entity.dorm;

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
     * 宿舍号
     */
    private String dormCode;

    /**
     * 维修类型(门窗、床、水电)
     */
    private Integer type;

    /**
     * 维修描述
     */
    private String description;

    /**
     * 维修图片
     */
    private String imgUrl;

    /**
     * 学号
     */
    private String studentCode;

    /**
     * 报修人联系方式
     */
    private String studentMobile;

    /**
     * 状态（0：未维修1：已维修2：已报废）
     */
    private Integer status;

    /**
     * 维修费用
     */
    private BigDecimal cost;

    /**
     * 维修费用发票
     */
    private String billUrl;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

    /**
     * 备注
     */
    private String memo;
}
