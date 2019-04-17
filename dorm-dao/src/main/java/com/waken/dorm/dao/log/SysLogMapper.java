package com.waken.dorm.dao.log;

import com.waken.dorm.common.entity.log.SysLog;
import com.waken.dorm.common.entity.log.SysLogExample;
import java.util.List;

import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.view.log.SysLogView;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper {
    List<SysLogView> listSysLogViews(SysLogForm sysLogForm);

    int countByExample(SysLogExample example);

    int deleteByExample(SysLogExample example);

    int deleteByPrimaryKey(String pkLogId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(String pkLogId);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}