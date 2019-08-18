package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormRepairMapper extends BaseMapper<DormRepair> {
    int batchUpdateStatus(Map<String, Object> param);

    List<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm);

    List<DormRepair> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}