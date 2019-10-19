package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;

/**
 * @ClassName DormViolationService
 * @Description DormViolationService
 * @Author zhaoRong
 * @Date 2019/4/2 12:48
 **/
public interface DormViolationService {
    /**
     * 新增宿舍违规记录
     *
     * @param addDormViolationForm
     * @return
     */
    DormViolation addDormViolation(AddDormViolationForm addDormViolationForm);

    /**
     * 删除宿舍违规记录
     *
     * @param deleteForm
     */
    void deleteDormViolation(DeleteForm deleteForm);

    /**
     * 分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    IPage<DormViolationView> listDormViolations(DormViolationForm dormViolationForm);

    /**
     * 修改宿舍违规记录
     *
     * @param updateDormViolationForm
     * @return
     */
    DormViolation updateDormViolation(UpdateDormViolationForm updateDormViolationForm);

    /**
     * app端分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    IPage<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm);
}
