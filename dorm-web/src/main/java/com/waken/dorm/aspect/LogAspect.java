package com.waken.dorm.aspect;

import com.waken.dorm.common.entity.log.SysLog;
import com.waken.dorm.common.utils.HttpContextUtils;
import com.waken.dorm.common.utils.IPUtils;
import com.waken.dorm.service.log.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LogAspect
 * @Description AOP 记录用户操作日志
 * @Author zhaoRong
 * @Date 2019/4/16 11:22
 **/
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.waken.dorm.common.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("LogAspect: AOP记录用户操作日志开始");
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        SysLog log = new SysLog();
        log.setIp(ip);
        log.setDuration((int) time);
        logService.saveLog(point, log);
        return result;
    }
}
