package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;

public interface DormService extends IService<Dorm> {
    /**
     * 保存/修改宿舍信息
     *
     * @param editDormForm
     * @return
     */
    Dorm saveDorm(EditDormForm editDormForm);

    /**
     * 删除宿舍
     *
     * @param deleteForm
     */
    void deleteDorm(DeleteForm deleteForm);

    /**
     * 分页查询宿舍信息
     *
     * @param dormForm
     * @return
     */
    IPage<DormView> listDorms(DormForm dormForm);

    /**
     * 查询出该宿舍所关联的班级中的已与该宿舍关联的学生 与未关联该宿舍的学生
     *
     * @param dormId
     * @return
     */
    DormStudentsView queryDormStudentsView(String dormId);

    /**
     * 批量新增宿舍与学生关联
     *
     * @param addDormStudentRelForm
     */
    void batchAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm);


    /**
     * app端查询宿舍视图
     *
     * @param studentId
     * @return
     */
    AppDormView queryAppDormView(String studentId);
}
