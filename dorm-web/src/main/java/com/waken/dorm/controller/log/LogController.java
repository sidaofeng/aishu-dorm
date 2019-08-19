package com.waken.dorm.controller.log;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.log.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 21:15
 **/
@Api(value = "后台日志相关接口", description = "后台日志相关接口(AiShu)")
@RestController
public class LogController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogService logService;

    @Log("删除日志")
    @CrossOrigin
    @PostMapping(value = "log/delete")
    @ApiOperation(value = "删除日志", notes = "删除日志 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteLog(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用日志删除接口：" + deleteFrom.toString());
        try {
            logService.deleteLog(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用日志删除接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @PostMapping(value = "log/page")
    @ApiOperation(value = "分页查询日志信息", notes = "分页查询日志信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Log.class)
    })
    public ResultView listSysLogViews(@RequestBody SysLogForm logForm) {
        logger.info("开始调用分页查询日志信息接口：" + logForm.toString());
        try {
            return ResultUtil.success(logService.listSysLogViews(logForm));
        } catch (Exception e) {
            logger.error("调用分页查询日志信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
