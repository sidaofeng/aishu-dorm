package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String id;
    /**
     * 校区
     */
    private String campusId;
    /**
     * 建筑物ID
     */
    private String buildingId;

    /**
     * 楼层ID（b_building_floor）
     */
    private String floorId;

    /**
     * 宿舍性别（1：男生寝室 2：女生寝室）
     */
    private Integer dormSex;

    /**
     * 宿舍状态（1使用，2闲置，3不可用）
     */
    private Integer status;

    /**
     * 宿舍类型（1学生宿舍、2教师宿舍、3宿管宿舍、4其他宿舍
     */
    private Integer type;
    /**
     * 关键字
     */
    private String keywords;
}
