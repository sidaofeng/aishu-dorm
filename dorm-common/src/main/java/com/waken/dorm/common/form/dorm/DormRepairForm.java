package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName DormRepairForm
 * @Description DormRepairForm
 * @Author zhaoRong
 * @Date 2019/4/1 21:02
 **/
@ToString
@Getter
@Setter
public class DormRepairForm extends BaseForm {
    private static final long serialVersionUID = -3982339641414695388L;
    private String dormId;

    private String studentId;
}
