package com.waken.dorm.common.view.dorm;

import com.wuwenze.poi.annotation.ExcelField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName: DormStudentView
 * @Description: 给宿舍分配学生的列表视图
 * @Author: zhaoRong
 * @Create: 2019/12/5 20:24
 **/
@Getter
@Setter
@ToString
public class DormStudentView {
    private String id;
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
     * 性别
     */
    @ExcelField(value = "性别")
    private Integer sex;

    /**
     * 年级
     */
    @ExcelField(value = "年级名称")
    private String gradeName;
    /**
     * 专业id
     */
    @ExcelField(value = "专业名称")
    private String majorName;
    /**
     * 班级Id
     */
    @ExcelField(value = "班级名称")
    private String className;

    @ExcelField(value = "宿舍编号")
    private String dormCode;

    @ExcelField(value = "床位名称")
    private String bedName;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;
}
