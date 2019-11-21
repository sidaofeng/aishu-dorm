package com.waken.dorm.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.waken.dorm.common.entity.basic.SysLog;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.view.log.SysLogView;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName LogService
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 20:48
 **/
public interface LogService {
    /**
     * 保存日志
     *
     * @param point
     * @param log
     * @throws JsonProcessingException
     */
    @Async
    void saveLog(ProceedingJoinPoint point, SysLog log) throws JsonProcessingException;

    /**
     * 删除日志
     *
     * @param deleteForm
     */
    void deleteLog(DeleteForm deleteForm);

    /**
     * 分页查询日志
     *
     * @param sysLogForm
     * @return
     */
    IPage<SysLogView> listSysLogViews(SysLogForm sysLogForm);


}
