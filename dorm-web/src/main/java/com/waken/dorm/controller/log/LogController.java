package com.waken.dorm.controller.log;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.log.SysLogForm;
import com.waken.dorm.common.view.log.SysLogView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.log.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LogController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 21:15
 **/
@Api(value = "后台日志相关接口", description = "后台日志相关接口(赵荣)")
@Controller
public class LogController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogService logService;

    @Log("删除日志")
    @CrossOrigin
    @RequestMapping(value= "/log/deleteLog",method= RequestMethod.POST)
    @ApiOperation(value = "deleteLog（删除日志）",notes = "删除日志 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteLog(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用日志删除接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            logService.deleteLog(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除日志成功");
        }catch (Exception e){
            logger.error("调用日志删除接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/log/listSysLogViews",method= RequestMethod.POST)
    @ApiOperation(value = "listSysLogViews（分页查询日志信息）",notes = "分页查询日志信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Log.class)
    })
    @ResponseBody
    public ResultView listSysLogViews(@RequestBody SysLogForm logForm){
        logger.info("开始调用分页查询日志信息接口："+logForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<SysLogView> pageInfo = logService.listSysLogViews(logForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询日志信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
