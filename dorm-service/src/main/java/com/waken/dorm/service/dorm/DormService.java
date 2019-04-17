package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;

public interface DormService {
    /**
     * 保存/修改宿舍信息
     * @param editDormForm
     * @return
     */
    public Dorm saveDorm(EditDormForm editDormForm);

    /**
     * 删除宿舍
     * @param deleteForm
     */
    public void deleteDorm(DeleteForm deleteForm);

    /**
     * 分页查询宿舍信息
     * @param dormForm
     * @return
     */
    public PageInfo<DormView> listDorms(DormForm dormForm);

    /**
     * 查询出该宿舍所关联的班级中的已与该宿舍关联的学生 与未关联该宿舍的学生
     * @param dormId
     * @return
     */
    public DormStudentsView queryDormStudentsView(String dormId);

    /**
     * 批量新增宿舍与学生关联
     * @param addDormStudentRelForm
     */
    public void batchAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm);

    /**
     * 通过学生id获取宿舍id
     * @param studentId
     */
    public String getDormIdByStudentId(String studentId);

    /**
     * app端查询宿舍视图
     * @param studentId
     * @return
     */
    public AppDormView queryAppDormView(String studentId);
}
