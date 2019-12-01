package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 建筑物楼层
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Getter
@Setter
@ToString
@TableName("rm_building_floor")
public class BuildingFloor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 建筑物ID
     */
    private String buildingId;

    /**
     * 名称
     */
    private String name;

    /**
     * 楼层
     */
    private Integer code;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

}
