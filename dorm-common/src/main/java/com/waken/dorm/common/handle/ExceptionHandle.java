package com.waken.dorm.common.handle;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.cache.CacheService;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.LimitAccessException;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName ExceptionHandle
 * @Description 统一异常处理类
 * @Author zhaoRong
 * @Date 2019/6/10 16:49
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @Autowired
    private CacheService cacheService;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxResponse handle(Exception e) {
        if (e instanceof ServerException) {
            ServerException serverException = (ServerException) e;
            log.error("服务端异常：{}", e.getMessage());
            return AjaxResponse.error(serverException.getCode(), serverException.getMessage());
        } else {
            log.error("未知错误：{}", e.getMessage());
            e.printStackTrace();
            return AjaxResponse.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @ExceptionHandler(value = LimitAccessException.class)
    @ResponseBody
    public AjaxResponse handle(LimitAccessException le) {
        log.error("接口访问超出频率限制: {}", le.getMessage());
        return AjaxResponse.error(ResultEnum.LIMIT);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public AjaxResponse handle(UnauthorizedException ue) {
        log.error("无权限异常：{}", ue.getMessage());
        return AjaxResponse.error(ResultEnum.UN_PERMS.getCode(), getErrorMsg(ue.getMessage()));
    }

    /**
     * 将shiro抛出的无权限异常信息做相关处理
     *
     * @param message
     * @return
     */
    private String getErrorMsg(String message) {
        String permsOrRoleCode = message.substring(message.indexOf("[") + 1, message.indexOf("]"));
        String permsOrRoleName;
        String errorMsg = "您没有对应操作权限，请联系管理员！";
        try {
            if (message.contains("not have role")) {
                Map<String, String> rolesMap = this.cacheService.getPermsOrRoleMap("rolesMap");
                if (rolesMap != null && !rolesMap.isEmpty()) {
                    permsOrRoleName = rolesMap.get(permsOrRoleCode);
                    return StringUtils.isNotEmpty(permsOrRoleName) ?
                            "您没有[" + permsOrRoleName + "]角色，请联系管理员！" : errorMsg;
                }
            } else if (message.contains("not have permission")) {
                Map<String, String> permsMap = this.cacheService.getPermsOrRoleMap("permsMap");
                if (permsMap != null && !permsMap.isEmpty()) {
                    permsOrRoleName = permsMap.get(permsOrRoleCode);
                    return StringUtils.isNotEmpty(permsOrRoleName) ?
                            "您没有[" + permsOrRoleName + "]权限，请联系管理员！" : errorMsg;
                }
            } else {
                return message;
            }
        } catch (Exception e) {
            log.error("获取缓存异常: ", e);
            return errorMsg;
        }
        return errorMsg;
    }
}
