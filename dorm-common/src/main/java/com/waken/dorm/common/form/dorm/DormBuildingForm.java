package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DormBuildingForm
 * @Description DormBuildingForm
 * @Author zhaoRong
 * @Date 2019/3/31 12:21
 **/
@ToString
@Getter
@Setter
public class DormBuildingForm extends BaseForm {
    private static final long serialVersionUID = -3547493633581949862L;
    private String pkDormBuildingId;

    private String schoolId;

    private String schoolName;

    private Integer dormBuildingType;

    private String dormBuildingNum;

    private Integer dormBuildingLevels;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;
}
