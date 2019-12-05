package com.waken.dorm.dao.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.basic.SysLog;
import com.waken.dorm.common.form.base.BaseForm;
import com.waken.dorm.common.view.log.SysLogView;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper extends BaseMapper<SysLog> {
    IPage<SysLogView> findPage(Page page, @Param("form") BaseForm form);
}