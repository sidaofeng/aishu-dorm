package com.waken.dorm.serviceImpl.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.log.SysLog;
import com.waken.dorm.common.entity.log.SysLogExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.log.SysLogView;
import com.waken.dorm.dao.log.SysLogMapper;
import com.waken.dorm.service.log.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @ClassName LogServiceImpl
 * @Description LogServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/15 20:48
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SysLogMapper logMapper;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    @Transactional
    public void saveLog(ProceedingJoinPoint joinPoint, SysLog log) throws JsonProcessingException {
        logger.info("service:开始进入保存系统日志接口");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            log.setOperationContent(logAnnotation.value());
        }
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            log.setParams(params.toString());
        }
        log.setLocation(AddressUtils.getCityInfo(log.getIp()));
        String pkId = UUIDUtils.getPkUUID();
        String curUserId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        log.setPkLogId(pkId);
        log.setUserId(curUserId);
        log.setCreateTime(curDate);
        // 保存系统日志
        logMapper.insertSelective(log);
    }

    /**
     * 删除日志
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteLog(DeleteForm deleteForm) {
        logger.info("service: 删除日志开始");
        List<String> logIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            SysLogExample example = new SysLogExample();
            SysLogExample.Criteria criteria = example.createCriteria();
            criteria.andPkLogIdIn(logIds);
            int count = Constant.ZERO;
            count = logMapper.deleteByExample(example);
            if (Constant.ZERO == count){
                throw new DormException("删除日志失败！");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询日志
     *
     * @param sysLogForm
     * @return
     */
    @Override
    public PageInfo<SysLogView> listSysLogViews(SysLogForm sysLogForm) {
        logger.info("service: 分页查询宿舍信息开始");
        if (sysLogForm.getStartTime() != null) {
            sysLogForm.setStartTime(DateUtils.formatDate2DateTimeStart(sysLogForm.getStartTime()));
        }
        if (sysLogForm.getEndTime() != null) {
            sysLogForm.setEndTime(DateUtils.formatDate2DateTimeEnd(sysLogForm.getEndTime()));
        }
        PageHelper.startPage(sysLogForm.getPageNum(),sysLogForm.getPageSize());
        List<SysLogView> sysLogViews = logMapper.listSysLogViews(sysLogForm);
        return new PageInfo<SysLogView>(sysLogViews);
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出NoSuchMethodException 异常则存在 toString 方法 ，安全的writeValueAsString ，否则 走 Object的 toString方法
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append("  ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append("  ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }
}
