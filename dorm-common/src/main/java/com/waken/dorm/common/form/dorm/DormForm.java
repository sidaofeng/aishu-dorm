package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DormForm
 * @Description DormForm
 * @Author zhaoRong
 * @Date 2019/3/31 13:11
 **/
@ToString
@Getter
@Setter
public class DormForm extends BaseForm {
    private static final long serialVersionUID = 5300270031224198511L;
    private String pkDormId;

    private String dormBuildingId;

    private Integer buildingLevelth;

    private Integer dormType;

    private String dormNum;

    private String dormDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
