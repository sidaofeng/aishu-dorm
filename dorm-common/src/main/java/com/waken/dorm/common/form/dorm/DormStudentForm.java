package com.waken.dorm.common.form.dorm;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName: DormStudentForm
 * @Description: 学生分配宿舍form
 * @Author: zhaoRong
 * @Create: 2019/12/5 20:32
 **/
@ToString
@Getter
@Setter
public class DormStudentForm extends BaseForm {

    /**
     * 0只查看未分配宿舍的学生，1查看只查看已分配宿舍的学生，其他任何值表示正常查询
     */
    private Integer unDistribution;
}
