package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DormBuildingMapper extends BaseMapper<Building> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<DormBuildingView> findBuildingPage(Page page, @Param("form") BuildingForm buildingForm);
}