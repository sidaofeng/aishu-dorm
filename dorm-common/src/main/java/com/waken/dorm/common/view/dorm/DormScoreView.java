package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormScoreView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/31 19:36
 **/
@Getter
@Setter
public class DormScoreView extends BaseView {
    private String pkDormScoreId;

    private String dormNum;

    private Integer cultureScore;

    private Integer disciplineScore;

    private Integer bedScore;

    private Integer deskScore;

    private Integer balconyScore;

    private Integer toiletScore;

    private Integer groundScore;

    private Integer doorWindowScore;

    private Integer metopeScore;

    private Integer totalScore;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
