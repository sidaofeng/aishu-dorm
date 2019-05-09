package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName AppDormViolationView
 * @Description AppDormViolationView
 * @Author zhaoRong
 * @Date 2019/4/3 11:39
 **/
@Getter
@Setter
public class AppDormViolationView {
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String memo;
}
