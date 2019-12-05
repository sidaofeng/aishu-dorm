package com.waken.dorm.common.entity.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 学生基本数据表
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Getter
@Setter
@ToString
@TableName("rm_student_basic")
@Excel("学生基本信息表")
public class StudentBasic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ExcelField(value = "姓名")
    private String name;

    /**
     * 学号
     */
    @ExcelField(value = "学号")
    private String code;

    /**
     * 身份证件号
     */
    @ExcelField(value = "身份证件号")
    private String cardId;

    /**
     * 性别
     */
    @ExcelField(value = "性别")
    private Integer sex;

    /**
     * 学生联系电话
     */
    @ExcelField(value = "联系电话")
    private String tel;

    /**
     * 电子信箱
     */
    @ExcelField(value = "电子信箱")
    private String email;

    /**
     * 层次（本科、专科）
     */
    @ExcelField(value = "层次")
    private String level;

    /**
     * 年级
     */
    @ExcelField(value = "年级名称")
    private String gradeId;
    /**
     * 专业id
     */
    @ExcelField(value = "专业名称")
    private String majorId;

    /**
     * 班级Id
     */
    @ExcelField(value = "班级名称")
    private String classId;

    /**
     * 民族
     */
    @ExcelField(value = "民族")
    private String race;

    /**
     * 籍贯
     */
    @ExcelField(value = "籍贯")
    private String nativePlace;

    /**
     * qq号
     */
    @ExcelField(value = "qq号")
    private Integer qq;

    /**
     * 家庭地址
     */
    @ExcelField(value = "家庭地址")
    private String familyAddress;

    /**
     * 家人电话
     */
    @ExcelField(value = "家人电话")
    private String familyTel;

    /**
     * 辅导员
     */
    @ExcelField(value = "辅导员")
    private String counselor;

    private String password;
    /**
     * 头像
     */
    private String imgUrl;

    private Boolean isDeleted;
}
