package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@TableName("rm_dorm_violation")
public class DormViolation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 规则id
     */
    @TableField("dorm_rule_id")
    private String dormRuleId;

    /**
     * 宿舍id
     */
    @TableField("dorm_id")
    private String dormId;

    /**
     * 学生id
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 违规图片url
     */
    @TableField("violation_img_url")
    private String violationImgUrl;

    /**
     * 违规原因
     */
    @TableField("violation_reason")
    private String violationReason;

    /**
     * 解决结果
     */
    @TableField("solve_result")
    private String solveResult;

    /**
     * 状态（1：已处理 0：未处理）
     */
    private Integer status;

    /**
     * 备注
     */
    private String memo;
}
