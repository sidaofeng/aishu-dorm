package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.utils.DozerMapper;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormRepairService;
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
 * @ClassName DormRepairController
 * @Description 后台管理宿舍维修相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 9:57
 **/
@Api(value = "后台管理宿舍维修相关接口", description = "后台管理宿舍维修相关接口(赵荣)")
@Controller
public class DormRepairController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRepairService dormRepairService;
    @Log("新增宿舍维修记录")
    @CrossOrigin
    @RequestMapping(value = {"dorm/addDormRepair"},method = RequestMethod.POST)
    @ApiOperation(value = "addDormRepair（新增宿舍维修记录）",notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepair.class)
    })
    @ResponseBody
    public ResultView addDormRepair(@RequestBody AddDormRepairForm addDormRepairForm){
        logger.info("开始调用新增宿舍维修记录接口："+addDormRepairForm.toString());
        ResultView resultView = new ResultView();
        try{
            addDormRepairForm.setTerminal(CodeEnum.WEB.getCode());
            DormRepair dormRepair = dormRepairService.addDormRepair(addDormRepairForm);
            logger.info("新增宿舍维修记录成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormRepair);
            resultView.setMsg("新增宿舍维修记录成功");
        }catch (Exception e){
            logger.info("新增宿舍维修记录失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("新增宿舍维修记录失败原因："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍维修信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDormRepair",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDormRepair（删除宿舍维修信息）",notes = "删除宿舍维修信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormRepair(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍维修信息接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dormRepairService.deleteDormRepair(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍维修信息成功");
            logger.info("删除宿舍维修信息成功");
        }catch (Exception e){
            logger.error("调用删除宿舍维修信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDormRepairs",method= RequestMethod.POST)
    @ApiOperation(value = "listDormRepairs（分页查询宿舍维修信息）",notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    @ResponseBody
    public ResultView listDormRepairs(@RequestBody DormRepairForm dormRepairForm){
        logger.info("开始调用分页查询宿舍维修信息接口："+dormRepairForm.toString());
        ResultView resultView = new ResultView();
        try{
            dormRepairForm.setTerminal(CodeEnum.WEB.getCode());
            PageInfo<DormRepairView> pageInfo = dormRepairService.listDormRepairs(dormRepairForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍维修信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @Log("修改宿舍维修")
    @CrossOrigin
    @RequestMapping(value = "/dorm/updateDormRepair", method = RequestMethod.POST)
    @ApiOperation(value = "updateDormRepair（修改宿舍维修）", notes = "修改宿舍维修 ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = DormRepair.class) })
    @ResponseBody
    public ResultView updateDormRepair(@RequestBody UpdateRepairForm updateRepairForm) {
        logger.info("开始调用修改宿舍维修接口：" + updateRepairForm.toString());
        ResultView resultView = new ResultView();
        try {
            DormRepair dormRepair = dormRepairService.updateDormRepair(updateRepairForm);
            resultView.setMsg("修改宿舍维修成功");
            resultView.setData(dormRepair);
        } catch (Exception e) {
            logger.error("修改宿舍维修失败，原因 :" + e.getMessage());
            e.printStackTrace();
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("修改宿舍维修失败");
        }
        return resultView;
    }
}
