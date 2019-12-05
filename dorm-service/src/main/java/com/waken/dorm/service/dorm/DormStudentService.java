package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.dorm.DormStudentForm;
import com.waken.dorm.common.form.dorm.StudentBedForm;
import com.waken.dorm.common.view.dorm.DormStudentView;

/**
 * @ClassName: DormStudentService
 * @Description: 床位分配学生业务层
 * @Author: zhaoRong
 * @Create: 2019/12/5 20:31
 **/
public interface DormStudentService extends IService<DormBed> {
    /**
     * 分页
     *
     * @param form
     * @return
     */
    IPage<DormStudentView> findPage(DormStudentForm form);

    Integer batchAdd(StudentBedForm form);
}
