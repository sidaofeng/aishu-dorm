package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.form.dorm.EditBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;

/**
 * 宿舍楼业务层
 */
public interface DormBuildingService extends IService<Building> {
    /**
     * 保存/修改宿舍楼信息
     *
     * @param editBuildingForm
     * @return
     */
    Building saveDormBuilding(EditBuildingForm editBuildingForm);

    /**
     * 删除宿舍楼
     *
     * @param deleteForm
     */
    void deleteDormBuilding(DeleteForm deleteForm);

    /**
     * 分页查询宿舍楼信息
     *
     * @param buildingForm
     * @return
     */
    IPage<DormBuildingView> listDormBuildings(BuildingForm buildingForm);
}
