package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormViolationService;
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
 * @ClassName DormViolationController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 13:39
 **/
@Api(value = "后台管理宿舍违规相关接口", description = "后台管理宿舍违规相关接口(赵荣)")
@Controller
public class DormViolationController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormViolationService dormViolationService;
    @Log("新增宿舍违规记录")
    @CrossOrigin
    @RequestMapping(value = {"dorm/addDormViolation"},method = RequestMethod.POST)
    @ApiOperation(value = "addDormViolation（新增宿舍违规记录）",notes = "新增宿舍违规记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolation.class)
    })
    @ResponseBody
    public ResultView addDormViolation(@RequestBody AddDormViolationForm addDormViolationForm){
        logger.info("开始调用新增宿舍违规记录接口："+addDormViolationForm.toString());
        ResultView resultView = new ResultView();
        try{
            DormViolation dormViolation = dormViolationService.addDormViolation(addDormViolationForm);
            logger.info("新增宿舍违规记录成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormViolation);
            resultView.setMsg("新增宿舍违规记录成功");
        }catch (Exception e){
            logger.info("新增宿舍违规记录失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("新增宿舍违规记录失败原因："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍违规信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDormViolation",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDormViolation（删除宿舍违规信息）",notes = "删除宿舍违规信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormViolation(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍违规信息接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dormViolationService.deleteDormViolation(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍违规信息成功");
            logger.info("删除宿舍违规信息成功");
        }catch (Exception e){
            logger.error("调用删除宿舍违规信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDormViolations",method= RequestMethod.POST)
    @ApiOperation(value = "listDormViolations（分页查询宿舍违规信息）",notes = "分页查询宿舍违规信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolationView.class)
    })
    @ResponseBody
    public ResultView listDormViolations(@RequestBody DormViolationForm dormViolationForm){
        logger.info("开始调用分页查询宿舍违规信息接口："+dormViolationForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<DormViolationView> pageInfo = dormViolationService.listDormViolations(dormViolationForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍违规信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @Log("修改宿舍违规")
    @CrossOrigin
    @RequestMapping(value = "/dorm/updateDormViolation", method = RequestMethod.POST)
    @ApiOperation(value = "updateDormViolation（修改宿舍违规）", notes = "修改宿舍违规 ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = DormViolation.class) })
    @ResponseBody
    public ResultView updateDormViolation(@RequestBody UpdateDormViolationForm updateViolationForm) {
        logger.info("开始调用修改宿舍违规接口：" + updateViolationForm.toString());
        ResultView resultView = new ResultView();
        try {
            DormViolation dormViolation = dormViolationService.updateDormViolation(updateViolationForm);
            resultView.setMsg("修改宿舍违规成功");
            resultView.setData(dormViolation);
        } catch (Exception e) {
            logger.error("修改宿舍违规失败，原因 :" + e.getMessage());
            e.printStackTrace();
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("修改宿舍违规失败");
        }
        return resultView;
    }

}
