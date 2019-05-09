package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
    private String pkDormScoreId;

    private String dormNum;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;

    private String memo;

    private String schoolId;

    private String studentId;
}
