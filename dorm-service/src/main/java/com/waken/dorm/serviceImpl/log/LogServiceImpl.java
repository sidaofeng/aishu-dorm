package com.waken.dorm.serviceImpl.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.entity.basic.SysLog;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.AddressUtils;
import com.waken.dorm.common.view.log.SysLogView;
import com.waken.dorm.dao.basic.SysLogMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.log.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
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
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {
    private final SysLogMapper logMapper;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public void saveLog(ProceedingJoinPoint joinPoint, SysLog sysLog) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperationContent(logAnnotation.value());
        }
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        String methodPath = className + "." + methodName + "()";
        log.info("执行方法耗时："+sysLog.getDuration()+" ms | 方法索引："+methodPath);
        sysLog.setMethod(methodPath);
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            sysLog.setParams(params.toString());
        }
        sysLog.setLocation(AddressUtils.getCityInfo(sysLog.getIp()));
        sysLog.setUserId(UserManager.getCurrentUserId());
        // 保存系统日志
        logMapper.insert(sysLog);
    }


    /**
     * 删除日志
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteLog(DeleteForm deleteForm) {
        log.info("service: 删除日志开始");
        List<String> logIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            logMapper.deleteBatchIds(logIds);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询日志
     *
     * @param sysLogForm
     * @return
     */
    @Override
    public IPage<SysLogView> listSysLogViews(SysLogForm sysLogForm) {

        return logMapper.listSysLogViews(DataHandle.getWrapperPage(sysLogForm),sysLogForm);
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
