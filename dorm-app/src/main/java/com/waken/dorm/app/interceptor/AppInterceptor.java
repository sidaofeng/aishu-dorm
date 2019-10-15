package com.waken.dorm.app.interceptor;

import com.waken.dorm.common.annotation.PrivilegeResource;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.AccessStrategy;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppInterceptor extends HandlerInterceptorAdapter {

    private final RedisCacheManager redisCacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的注解
            Method method = handlerMethod.getMethod();
            PrivilegeResource privilegeResource = method.getAnnotation(PrivilegeResource.class);
            if (null != privilegeResource) {
                //Anon注解不做拦截
                if (AccessStrategy.Anon.equals(privilegeResource.strategy())) {
                    return true;
                }
            }
        }
        String studentToken = request.getHeader(CacheConstant.STUDENT_TOKEN);
        if (StringUtils.isEmpty(studentToken)) {
            throw new ServerException("学生登陆token为空！");
        }
        boolean isExistsToken = redisCacheManager.exists(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        if (!isExistsToken) {
            throw new ServerException(ResultEnum.TIME_OUT);
        }
        StudentInfo studentInfo = (StudentInfo) redisCacheManager.get(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        if (studentInfo == null) {
            throw new ServerException(ResultEnum.TIME_OUT);
        } else {
            return true;
        }

    }
}