package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.view.dorm.BuildingView;

import java.util.List;

/**
 * 宿舍楼业务层
 */
public interface BuildingService extends IService<Building> {
    /**
     * 新增
     * @param building
     * @return
     */
    int insert(Building building);

    /**
     * 删除
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param building
     * @return
     */
    int update(Building building);

    /**
     * 通过id获取建筑信息
     *
     * @param id
     * @return
     */
    Building get(String id);

    /**
     * 通过校区id查询建筑集合
     *
     * @param campusId
     * @return
     */
    List<Building> list(String campusId);

    /**
     * 分页
     *
     * @param buildingForm
     * @return
     */
    IPage<BuildingView> page(BuildingForm buildingForm);
}
