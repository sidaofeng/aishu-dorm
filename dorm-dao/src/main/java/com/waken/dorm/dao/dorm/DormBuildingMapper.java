package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormBuildingMapper extends BaseMapper<DormBuilding> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<DormBuildingView> listDormBuildings(Page page,@Param("form") DormBuildingForm buildingForm);

    List<DormBuilding> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}