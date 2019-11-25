package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName UpdateDormViolationForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 12:57
 **/
@ToString
@Getter
@Setter
public class UpdateDormViolationForm {
    private String id;

    private Integer status;

    private String memo;
}
