package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.annotation.Id;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
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
@Excel("宿舍评分信息统计表")
@TableName("rm_dorm_score")
public class DormScore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @TableId("pk_dorm_score_id")
    private String pkDormScoreId;

    /**
     * 宿舍号
     */
    @ExcelField(value = "宿舍号")
    @TableField("dorm_num")
    private String dormNum;

    /**
     * 宿舍文化得分
     */
    @ExcelField(value = "宿舍文化")
    @TableField("culture_score")
    private Integer cultureScore;

    /**
     * 纪律得分
     */
    @ExcelField(value = "纪律")
    @TableField("discipline_score")
    private Integer disciplineScore;

    /**
     * 宿舍床铺得分
     */
    @ExcelField(value = "床铺")
    @TableField("bed_score")
    private Integer bedScore;

    /**
     * 书桌得分
     */

    @ExcelField(value = "书桌")
    @TableField("desk_score")
    private Integer deskScore;

    /**
     * 阳台得分
     */
    @ExcelField(value = "阳台")
    @TableField("balcony_score")
    private Integer balconyScore;

    /**
     * 厕所得分
     */
    @ExcelField(value = "厕所")
    @TableField("toilet_score")
    private Integer toiletScore;

    /**
     * 地面得分
     */
    @ExcelField(value = "地面")
    @TableField("ground_score")
    private Integer groundScore;

    /**
     * 门窗得分
     */
    @ExcelField(value = "门窗")
    @TableField("door_window_score")
    private Integer doorWindowScore;

    /**
     * 墙面得分
     */
    @ExcelField(value = "墙面")
    @TableField("metope_score")
    private Integer metopeScore;

    /**
     * 总计得分
     */
    @ExcelField(value = "总计")
    @TableField("total_score")
    private Integer totalScore;

    /**
     * 状态（1生效 0 失效）
     */
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;

    /**
     * 备注
     */
    @ExcelField(value = "备注")
    private String memo;
}
