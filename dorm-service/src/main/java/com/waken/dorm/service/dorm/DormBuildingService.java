package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;

/**
 * 宿舍楼业务层
 */
public interface DormBuildingService {
    /**
     * 保存/修改宿舍楼信息
     *
     * @param editDormBuildingForm
     * @return
     */
    public DormBuilding saveDormBuilding(EditDormBuildingForm editDormBuildingForm);

    /**
     * 删除宿舍楼
     *
     * @param deleteForm
     */
    public void deleteDormBuilding(DeleteForm deleteForm);

    /**
     * 分页查询宿舍楼信息
     *
     * @param dormBuildingForm
     * @return
     */
    public PageInfo<DormBuildingView> listDormBuildings(DormBuildingForm dormBuildingForm);
}
