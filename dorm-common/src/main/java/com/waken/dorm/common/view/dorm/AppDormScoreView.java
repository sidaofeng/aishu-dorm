package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName AppDormScoreView
 * @Description AppDormScoreView
 * @Author zhaoRong
 * @Date 2019/4/3 13:00
 **/
@ToString
@Getter
@Setter
public class AppDormScoreView extends BaseView {
    private String id;

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

    private String memo;
}
