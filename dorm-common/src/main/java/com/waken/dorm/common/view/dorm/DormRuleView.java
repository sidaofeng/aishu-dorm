package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormRuleView
 * @Description DormRuleView
 * @Author zhaoRong
 * @Date 2019/4/3 13:40
 **/
@Getter
@Setter
public class DormRuleView extends BaseView {
    private String pkDormRuleId;

    private String schoolName;

    private String ruleName;

    private String ruleDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
