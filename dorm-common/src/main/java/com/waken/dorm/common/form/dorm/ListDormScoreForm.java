package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ListDormScoreForm
 * @Description ListDormScoreForm
 * @Author zhaoRong
 * @Date 2019/3/31 19:36
 **/
@ToString
@Getter
@Setter
public class ListDormScoreForm extends BaseForm {
    private static final long serialVersionUID = -223148826994314229L;

    private String studentId;

    private String dormId;

    private String dormCode;

}
