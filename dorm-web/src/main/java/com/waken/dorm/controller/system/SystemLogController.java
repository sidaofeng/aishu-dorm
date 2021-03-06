package com.waken.dorm.controller.system;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.form.base.BaseForm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.basic.LogService;
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
 * @Description 日志管理
 * @Author zhaoRong
 * @Date 2019/4/15 21:15
 **/
@Slf4j
@Api(value = "日志管理", description = "系统管理-日志管理")
@RestController
public class SystemLogController extends BaseController {
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
    public AjaxResponse findPage(@RequestBody BaseForm form) {

        return AjaxResponse.success(logService.findPage(form));
    }
}
