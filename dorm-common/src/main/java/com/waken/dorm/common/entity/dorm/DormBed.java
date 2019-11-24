package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 宿舍床位
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Getter
@Setter
@ToString
@TableName("rm_dorm_bed")
public class DormBed extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 宿舍id
     */
    private String dormId;

    /**
     * 床位名称
     */
    private String name;

    /**
     * 床位号
     */
    private String code;

    /**
     * 入住人id（学生/教师/宿管/其他）
     */
    private String subjectId;

    /**
     * 创建时间
     */
    private Date createTime;

    private String createUserId;

    /**
     * 最终修改时间
     */
    private Date lastModifyTime;

    private String lastModifyUserId;

    /**
     * 备注
     */
    private String memo;

}
