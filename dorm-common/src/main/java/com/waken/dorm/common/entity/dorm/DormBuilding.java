package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * @ClassName DormBuilding
 * @Description 宿舍楼实体类
 * @Author zhaoRong
 * @Date 2019/3/31 10:48
 **/
@Getter
@Setter
public class DormBuilding extends BaseEntity {
    private static final long serialVersionUID = 8862025902942253471L;
    private String pkDormBuildingId;

    private String schoolId;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String memo;
}