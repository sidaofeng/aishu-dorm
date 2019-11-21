package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditDormForm
 * @Description EditDormForm
 * @Author zhaoRong
 * @Date 2019/3/31 13:11
 **/
@ToString
@Getter
@Setter
public class EditDormForm {
    private String id;

    private String dormBuildingId;

    private Integer buildingLevelth;

    private Integer dormType;

    private String dormNum;

    private String dormDesc;

    private Integer status;

    private String memo;
}
