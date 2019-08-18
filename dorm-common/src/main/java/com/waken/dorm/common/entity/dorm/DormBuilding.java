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
@TableName("rm_dorm_building")
public class DormBuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_dorm_building_id")
    private String pkDormBuildingId;

    /**
     * 楼栋类型（0：校内 1：校外）
     */
    @TableField("dorm_building_type")
    private Integer dormBuildingType;

    /**
     * 楼栋编号（1，2，A，B）
     */
    @TableField("dorm_building_num")
    private String dormBuildingNum;

    /**
     * 楼栋总层数
     */
    @TableField("dorm_building_levels")
    private Integer dormBuildingLevels;

    /**
     * 楼栋描述
     */
    @TableField("dorm_building_desc")
    private String dormBuildingDesc;

    /**
     * 状态 (1-生效，0-无效)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    /**
     * 最终修改时间
     */
    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;

    /**
     * 备注
     */
    private String memo;
}
