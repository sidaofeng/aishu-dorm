package com.waken.dorm.common.entity.dorm;

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
     * 宿舍号
     */
    private String dormCode;

    /**
     * 学号
     */
    private String studentCode;

    /**
     * 违规图片url
     */
    private String imgUrl;

    /**
     * 违规原因
     */
    private String reason;

    /**
     * 解决结果
     */
    private String result;

    /**
     * 状态（1：已处理 0：未处理）
     */
    private Integer status;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;


    /**
     * 备注
     */
    private String memo;

}
