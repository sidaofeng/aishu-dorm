package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormRepairMapper extends BaseMapper<DormRepair> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<DormRepairView> listDormRepairs(Page page,@Param("form") DormRepairForm dormRepairForm);

    List<DormRepair> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}