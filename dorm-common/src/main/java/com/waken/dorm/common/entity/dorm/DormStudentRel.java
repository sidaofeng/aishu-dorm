package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("rm_dorm_student_rel")
public class DormStudentRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_dorm_student_id")
    private String pkDormStudentId;

    /**
     * 宿舍id
     */
    @TableField("dorm_id")
    private String dormId;

    /**
     * 学生id
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 状态(1生效0失效)
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
    private String memo;

}
