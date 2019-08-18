package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormViolationView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 9:41
 **/
@Getter
@Setter
public class DormViolationView extends BaseView {
    private String pkDormViolationId;

    private String dormRuleName;

    private String dormNum;

    private String studentName;

    private String violationImgUrl;

    private String violationReason;

    private String solveResult;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
