package com.waken.dorm.common.form.log;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName SysLogForm
 * @Description SysLogForm
 * @Author zhaoRong
 * @Date 2019/4/16 13:03
 **/
@ToString
@Getter
@Setter
public class SysLogForm extends BaseForm {
    private String id;

    private String keyWord;
}
