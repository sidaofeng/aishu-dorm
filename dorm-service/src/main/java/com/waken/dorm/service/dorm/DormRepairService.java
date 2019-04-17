package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;

public interface DormRepairService {
    /**
     * 新增维修记录
     * @param addDormRepairForm
     * @return
     */
    public DormRepair addDormRepair(AddDormRepairForm addDormRepairForm);

    /**
     * 删除维修记录
     * @param deleteForm
     */
    public void deleteDormRepair(DeleteForm deleteForm);
    /**
     * 分页查询宿舍维修记录
     * @param dormRepairForm
     * @return
     */
    public PageInfo<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm);

    /**
     * 更新宿舍维修记录（提交维修结果）
     * @param updateRepairForm
     * @return
     */
    public DormRepair updateDormRepair(UpdateRepairForm updateRepairForm);
}
