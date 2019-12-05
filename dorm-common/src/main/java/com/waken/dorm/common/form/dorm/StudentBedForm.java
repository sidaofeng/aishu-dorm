package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName: StudentBedForm
 * @Description:
 * @Author: zhaoRong
 * @Create: 2019/12/5 21:13
 **/
@Getter
@Setter
@ToString
public class StudentBedForm {
    private List<String> studentIdList;
    private List<String> bedIdList;
}
