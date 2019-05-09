package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.annotation.ExcelColumn;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DormScore extends BaseEntity {
    private static final long serialVersionUID = -1442722153675048338L;
    private String pkDormScoreId;

    private String dormId;

    @ExcelColumn(name = "宿舍号", column = "A")
    private String dormNum;

    @ExcelColumn(name = "寝室文化", column = "B")
    private Integer cultureScore;

    @ExcelColumn(name = "纪律", column = "C")
    private Integer disciplineScore;

    @ExcelColumn(name = "床铺", column = "D")
    private Integer bedScore;

    @ExcelColumn(name = "书桌", column = "E")
    private Integer deskScore;

    @ExcelColumn(name = "阳台", column = "F")
    private Integer balconyScore;

    @ExcelColumn(name = "厕所", column = "G")
    private Integer toiletScore;

    @ExcelColumn(name = "地面", column = "H")
    private Integer groundScore;

    @ExcelColumn(name = "门窗", column = "I")
    private Integer doorWindowScore;

    @ExcelColumn(name = "墙面", column = "J")
    private Integer metopeScore;

    @ExcelColumn(name = "总分", column = "K")
    private Integer totalScore;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    @ExcelColumn(name = "备注", column = "L")
    private String memo;
}