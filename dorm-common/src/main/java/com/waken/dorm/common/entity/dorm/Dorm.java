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
@TableName("rm_dorm")
public class Dorm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_dorm_id")
    private String pkDormId;

    /**
     * 宿舍楼id
     */
    @TableField("dorm_building_id")
    private String dormBuildingId;

    /**
     * 楼栋层数(第几层)
     */
    @TableField("building_levelth")
    private Integer buildingLevelth;

    /**
     * 宿舍类型（1：男生寝室 2：女生寝室 3：教师宿舍 4：宿管宿舍 5：存放物品宿舍）
     */
    @TableField("dorm_type")
    private Integer dormType;

    /**
     * 宿舍编号
     */
    @TableField("dorm_num")
    private String dormNum;

    /**
     * 宿舍描述
     */
    @TableField("dorm_desc")
    private String dormDesc;

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
