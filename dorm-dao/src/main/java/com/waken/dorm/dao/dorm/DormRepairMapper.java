package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.entity.dorm.DormRepairExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;
import org.apache.ibatis.annotations.Param;

public interface DormRepairMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm);

    List<DormRepair> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DormRepairExample example);

    int deleteByExample(DormRepairExample example);

    int deleteByPrimaryKey(String pkDormRepairId);

    int insert(DormRepair record);

    int insertSelective(DormRepair record);

    List<DormRepair> selectByExample(DormRepairExample example);

    DormRepair selectByPrimaryKey(String pkDormRepairId);

    int updateByExampleSelective(@Param("record") DormRepair record, @Param("example") DormRepairExample example);

    int updateByExample(@Param("record") DormRepair record, @Param("example") DormRepairExample example);

    int updateByPrimaryKeySelective(DormRepair record);

    int updateByPrimaryKey(DormRepair record);
}