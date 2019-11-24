package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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

    private String id;

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
    private String code;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

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

}
