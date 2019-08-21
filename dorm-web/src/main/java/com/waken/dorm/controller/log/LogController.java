package com.waken.dorm.controller.log;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.log.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 21:15
 **/
@Slf4j
@Api(value = "后台日志相关接口", description = "后台日志相关接口(AiShu)")
@RestController
public class LogController extends BaseController {
    @Autowired
    LogService logService;

    @Log("删除日志")
    @CrossOrigin
    @PostMapping(value = "log/delete")
    @ApiOperation(value = "删除日志", notes = "删除日志 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteLog(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用日志删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        logService.deleteLog(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "log/page")
    @ApiOperation(value = "分页查询日志信息", notes = "分页查询日志信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Log.class)
    })
    public AjaxResponse listSysLogViews(@RequestBody SysLogForm logForm) {
        log.info("开始调用分页查询日志信息接口：" + logForm.toString());
        return AjaxResponse.success(logService.listSysLogViews(logForm));
    }
}
