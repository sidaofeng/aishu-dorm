package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DormStudentRelView
 * @Description DormStudentRelView
 * @Author zhaoRong
 * @Date 2019/3/31 14:22
 **/
@Getter
@Setter
public class DormStudentRelView extends BaseView {
    private String pkDormStudentId;

    private String studentId;

    private String studentName;

    private String studentNum;
}
