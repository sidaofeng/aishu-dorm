package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 宿舍基本信息
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Data
@TableName("rm_dorm")
public class Dorm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 建筑物ID
     */
    private String buildingId;

    /**
     * 楼层ID（b_building_floor）
     */
    private String floorId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 宿舍性别（1：男生寝室 2：女生寝室）
     */
    private String dormSex;

    /**
     * 宿舍状态（1使用，2闲置，3不可用）
     */
    private Integer status;

    /**
     * 宿舍类型（1学生宿舍、2教师宿舍、3宿管宿舍、4其他宿舍
     */
    private Integer type;

    /**
     * 床位数量
     */
    private Integer bedNum;

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

    /**
     * 备注
     */
    private String memo;
}
