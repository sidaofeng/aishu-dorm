package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName BuildingView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 14:54
 **/
@Getter
@Setter
public class BuildingView extends BaseView {
    private String id;

    /**
     * 校区
     */
    private String campusName;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 起始楼层
     */
    private Integer floorStart;

    /**
     * 楼层数量
     */
    private Integer floorTotal;

    /**
     * 使用状态(1使用，2闲置)
     */
    private Integer status;

    /**
     * 建筑类型 1校内，2校外
     */
    private Integer type;

    /**
     * 产权证号
     */
    private String certificateCode;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
