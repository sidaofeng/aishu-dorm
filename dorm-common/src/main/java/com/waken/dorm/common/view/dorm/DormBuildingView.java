package com.waken.dorm.common.view.dorm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormBuildingView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 14:54
 **/
@Getter
@Setter
public class DormBuildingView {
    private String pkDormBuildingId;

    private String schoolName;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;
}
