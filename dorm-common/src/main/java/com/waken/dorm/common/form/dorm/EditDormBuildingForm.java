package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditDormBuildingForm
 * @Description 编辑宿舍楼信息form表单
 * @Author zhaoRong
 * @Date 2019/3/31 10:48
 **/
@ToString
@Getter
@Setter
public class EditDormBuildingForm {
    private String pkDormBuildingId;

    private String schoolId;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private String dormBuildingDesc;

    private Integer status;

    private String memo;
}
