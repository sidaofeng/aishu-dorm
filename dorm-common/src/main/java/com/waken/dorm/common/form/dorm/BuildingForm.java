package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName BuildingForm
 * @Description BuildingForm
 * @Author zhaoRong
 * @Date 2019/3/31 12:21
 **/
@ToString
@Getter
@Setter
public class BuildingForm extends BaseForm {
    private static final long serialVersionUID = -3547493633581949862L;

    private String id;

    /**
     * 校区
     */
    private String campusId;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 使用状态(1使用，2闲置)
     */
    private String status;

    /**
     * 建筑类型 1校内，2校外
     */
    private String type;
}
