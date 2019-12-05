package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.view.dorm.DormViolationView;
import org.apache.ibatis.annotations.Param;

public interface DormViolationMapper extends BaseMapper<DormViolation> {

    IPage<DormViolationView> findPage(Page page, @Param("form") DormViolationForm dormViolationForm);

}