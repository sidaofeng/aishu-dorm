package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.view.dorm.BuildingView;
import org.apache.ibatis.annotations.Param;

public interface BuildingMapper extends BaseMapper<Building> {
    /**
     * 分页
     *
     * @param page
     * @param buildingForm
     * @return
     */
    IPage<BuildingView> findBuildingPage(Page page, @Param("form") BuildingForm buildingForm);
}