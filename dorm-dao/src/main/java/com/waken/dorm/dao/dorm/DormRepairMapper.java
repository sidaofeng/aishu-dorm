package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;
import org.apache.ibatis.annotations.Param;

public interface DormRepairMapper extends BaseMapper<DormRepair> {

    IPage<DormRepairView> findPage(Page page, @Param("form") DormRepairForm dormRepairForm);

}