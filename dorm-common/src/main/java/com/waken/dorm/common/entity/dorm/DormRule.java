package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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
@TableName("rm_dorm_rule")
public class DormRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("pk_dorm_rule_id")
    private String pkDormRuleId;

    @TableField("rule_name")
    private String ruleName;

    @TableField("rule_desc")
    private String ruleDesc;

    /**
     * 状态（1：生效 0：失效）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_user_id")
    private String createUserId;

    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;

    /**
     * 备注
     */
    private String memo;

}
