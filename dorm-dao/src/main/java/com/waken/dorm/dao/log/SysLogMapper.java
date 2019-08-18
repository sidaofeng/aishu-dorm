package com.waken.dorm.dao.log;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.log.SysLog;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.view.log.SysLogView;

import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog> {
    List<SysLogView> listSysLogViews(SysLogForm sysLogForm);
}