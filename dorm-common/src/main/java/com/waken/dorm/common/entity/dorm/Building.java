package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Data
@TableName("rm_building")
public class Building extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

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
    private String status;

    /**
     * 建筑类型 1校内，2校外
     */
    private String type;

    /**
     * 产权证号
     */
    private String certificateCode;

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
