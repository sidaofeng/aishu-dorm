package com.waken.dorm.service.school;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.school.School;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolForm;
import com.waken.dorm.common.form.school.SchoolForm;
import com.waken.dorm.common.view.school.SchoolView;

/**
 * @ClassName SchoolService
 * @Description SchoolService
 * @Author zhaoRong
 * @Date 2019/4/1 18:10
 **/
public interface SchoolService {
    /**
     * 保存或修改学习信息
     * @param editSchoolForm
     * @return
     */
    public School saveSchool(EditSchoolForm editSchoolForm);

    /**
     * 删除学校
     * @param deleteForm
     */
    public void deleteSchool(DeleteForm deleteForm);

    /**
     * 分页查询学校信息
     * @param schoolForm
     * @return
     */
    public PageInfo<SchoolView> listSchoolView(SchoolForm schoolForm);

    /**
     * 获取当前登陆用户对应的学校的信息
     * @return
     */
    public String getSchoolIdByUserId(String userId);
}
