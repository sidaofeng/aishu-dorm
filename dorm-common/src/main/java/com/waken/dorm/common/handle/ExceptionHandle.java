package com.waken.dorm.common.handle;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.LimitAccessException;
import com.waken.dorm.common.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionHandle
 * @Description 统一异常处理类
 * @Author zhaoRong
 * @Date 2019/6/10 16:49
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

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
        String errorMsg;
        if (message.contains("role")) {
            errorMsg = "您没有对应的角色权限 ";
        } else if (message.contains("permission")) {
            errorMsg = "您没有对应的操作权限 ";
        } else {
            return message;
        }
        errorMsg = errorMsg + message.substring(message.indexOf("["), message.indexOf("]") + 1);
        log.error(errorMsg);
        return errorMsg;
    }
}
