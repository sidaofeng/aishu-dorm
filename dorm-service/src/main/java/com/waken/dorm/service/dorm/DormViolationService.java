package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
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
     * @param addDormViolationForm
     * @return
     */
    public DormViolation addDormViolation(AddDormViolationForm addDormViolationForm);

    /**
     * 删除宿舍违规记录
     * @param deleteForm
     */
    public void deleteDormViolation(DeleteForm deleteForm);

    /**
     * 分页查询宿舍违规记录
     * @param dormViolationForm
     * @return
     */
    public PageInfo<DormViolationView> listDormViolations(DormViolationForm dormViolationForm);

    /**
     * 修改宿舍违规记录
     * @param updateDormViolationForm
     * @return
     */
    public DormViolation updateDormViolation(UpdateDormViolationForm updateDormViolationForm);

    /**
     * app端分页查询宿舍违规记录
     * @param dormViolationForm
     * @return
     */
    public PageInfo<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm);
}
