package com.waken.dorm.common.entity.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 学生基本数据表
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Data
@TableName("rm_student_basic")
public class StudentBasic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学生基本数据表id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学号
     */
    private String code;

    /**
     * 身份证件号
     */
    private String idCard;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 学生联系电话
     */
    private String tel;

    /**
     * 电子信箱
     */
    private String email;

    /**
     * 层次（本科、专科）
     */
    private String level;

    /**
     * 班级名称
     */
    private String className;

    private String race;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * qq号
     */
    private Integer qq;

    /**
     * 家庭地址
     */
    private String familyAddress;

    /**
     * 家人电话
     */
    private String familyTel;

    /**
     * 辅导员
     */
    private String counselor;

    private String password;

    /**
     * 头像
     */
    private String imgUrl;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime lastModifyTime;

    private String lastModifyUserId;
}
