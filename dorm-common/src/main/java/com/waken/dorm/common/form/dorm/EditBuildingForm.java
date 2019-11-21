package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditBuildingForm
 * @Description 编辑宿舍楼信息form表单
 * @Author zhaoRong
 * @Date 2019/3/31 10:48
 **/
@ToString
@Getter
@Setter
public class EditBuildingForm {
    private String id;

    /**
     * 校区
     */
    private String campusId;

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
    private String status;

    /**
     * 建筑类型 1校内，2校外
     */
    private String type;

    /**
     * 产权证号
     */
    private String certificateCode;

    /**
     * 备注
     */
    private String memo;
}
