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
 * @since 2019-11-21
 */
@Getter
@Setter
@ToString
@TableName("rm_building")
public class Building extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 校区
     */
    private String campusId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 起始楼层
     */
    private Integer floorStart;

    /**
     * 楼层数量
     */
    private Integer floorTotal;

    /**
     * 使用状态(1使用，2闲置)
     */
    private Integer status;

    /**
     * 建筑类型 1校内，2校外
     */
    private Integer type;

    /**
     * 产权证号
     */
    private String certificateCode;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

    /**
     * 备注
     */
    private String memo;
}
