package com.waken.dorm.common.entity.student;

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
@TableName("rm_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_student_id")
    private String pkStudentId;

    /**
     * 学校姓名
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 学号
     */
    @TableField("student_num")
    private Integer studentNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 状态 (1-生效，0-无效)
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

}
