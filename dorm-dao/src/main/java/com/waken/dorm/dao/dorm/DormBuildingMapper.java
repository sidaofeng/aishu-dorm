package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.entity.dorm.DormBuildingExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import org.apache.ibatis.annotations.Param;

public interface DormBuildingMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<DormBuildingView> listDormBuildings(DormBuildingForm buildingForm);

    List<DormBuilding> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DormBuildingExample example);

    int deleteByExample(DormBuildingExample example);

    int deleteByPrimaryKey(String pkDormBuildingId);

    int insert(DormBuilding record);

    int insertSelective(DormBuilding record);

    List<DormBuilding> selectByExample(DormBuildingExample example);

    DormBuilding selectByPrimaryKey(String pkDormBuildingId);

    int updateByExampleSelective(@Param("record") DormBuilding record, @Param("example") DormBuildingExample example);

    int updateByExample(@Param("record") DormBuilding record, @Param("example") DormBuildingExample example);

    int updateByPrimaryKeySelective(DormBuilding record);

    int updateByPrimaryKey(DormBuilding record);
}