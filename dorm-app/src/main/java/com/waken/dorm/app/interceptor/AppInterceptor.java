package com.waken.dorm.app.interceptor;

import com.waken.dorm.common.annotation.PrivilegeResource;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.AccessStrategy;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description 拦截APP端请求，实现访问控制
 * @Author zhaoRong
 * @Date 2019/8/6 22:20
 **/
@Slf4j
public class AppInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisCacheManager redisCacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("APP拦截开始------");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();//获取方法上的注解
            PrivilegeResource privilegeResource = method.getAnnotation(PrivilegeResource.class);
            if (null != privilegeResource) {
                if (AccessStrategy.Guest.equals(privilegeResource.strategy())) {//匿名用户不做拦截
                    return true;
                }
            }
        }
        String studentToken = request.getHeader(CacheConstant.STUDENT_TOKEN);
        if (StringUtils.isEmpty(studentToken)) {
            throw new ServerException(ResultEnum.UN_AUTH);
        }
        boolean isExistsToken = redisCacheManager.exists(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        if (isExistsToken == false) {
            log.info("登录过期");
            throw new ServerException(ResultEnum.TIME_OUT);
        }
        StudentInfo studentInfo = (StudentInfo) redisCacheManager.get(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        if (studentInfo == null) {
            log.info("登录过期");
            throw new ServerException(ResultEnum.TIME_OUT);
        } else {
            return true;
        }

    }
}