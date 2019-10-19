package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;

public interface DormRepairService {
    /**
     * 新增维修记录
     *
     * @param addDormRepairForm
     * @return
     */
    DormRepair addDormRepair(AddDormRepairForm addDormRepairForm);

    /**
     * 删除维修记录
     *
     * @param deleteForm
     */
    void deleteDormRepair(DeleteForm deleteForm);

    /**
     * 分页查询宿舍维修记录
     *
     * @param dormRepairForm
     * @return
     */
    IPage<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm);

    /**
     * 更新宿舍维修记录（提交维修结果）
     *
     * @param updateRepairForm
     * @return
     */
    DormRepair updateDormRepair(UpdateRepairForm updateRepairForm);
}
