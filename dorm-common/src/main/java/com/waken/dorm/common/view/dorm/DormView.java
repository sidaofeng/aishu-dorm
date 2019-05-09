package com.waken.dorm.common.view.dorm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 12:19
 **/
@Getter
@Setter
public class DormView {
    private String pkDormId;

    private String dormBuildingNum;

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
