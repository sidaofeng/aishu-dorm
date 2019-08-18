package com.waken.dorm.common.handle;

import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.ResultUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionHandle
 * @Description 统一异常处理类
 * @Author zhaoRong
 * @Date 2019/6/10 16:49
 **/
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultView handle(Exception e) {
        if (e instanceof ServerException) {
            ServerException serverException = (ServerException) e;
            logger.info("服务端异常：" + e);
            return ResultUtil.error(serverException.getCode(), serverException.getMessage());
        } else {
            e.printStackTrace();
            logger.info("未知错误：" + e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public ResultView handle(UnauthorizedException ue) {
        logger.info("无权限异常：" + ue.getMessage());
        return ResultUtil.error(ResultEnum.UN_PERMS.getCode(), getErrorMsg(ue.getMessage()));
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
        logger.info(errorMsg);
        return errorMsg;
    }
}
