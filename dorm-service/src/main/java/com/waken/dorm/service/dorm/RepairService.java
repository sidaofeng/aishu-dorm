package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;

public interface RepairService extends IService<DormRepair> {
    /**
     * 新增
     *
     * @param repair
     * @return
     */
    int insert(DormRepair repair);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param repair
     * @return
     */
    int update(DormRepair repair);

    /**
     * 通过id获取维修信息
     *
     * @param id
     * @return
     */
    DormRepair get(String id);

    /**
     * 分页查询宿舍维修记录
     *
     * @param dormRepairForm
     * @return
     */
    IPage<DormRepairView> findPage(DormRepairForm dormRepairForm);
}
