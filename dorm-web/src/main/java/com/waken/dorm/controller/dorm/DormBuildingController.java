package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormBuildingService;
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
 * @ClassName DormBuildingController
 * @Description 宿舍楼模块相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Api(value = "宿舍楼模块相关接口", description = "宿舍楼模块相关接口(赵荣)")
@Controller
public class DormBuildingController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DormBuildingService buildingService;
    @Log("(保存/修改)宿舍楼信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/saveDormBuilding",method= RequestMethod.POST)
    @ApiOperation(value = "saveDormBuilding（(保存/修改)宿舍楼信息）",notes = "(保存/修改)宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBuilding.class)
    })
    @ResponseBody
    public ResultView saveDormBuilding(@RequestBody EditDormBuildingForm editDormBuildingForm){
        logger.info("开始调用(保存/修改)宿舍楼信息接口接口："+editDormBuildingForm.toString());
        ResultView resultView = new ResultView();
        try{
            DormBuilding dormBuilding = buildingService.saveDormBuilding(editDormBuildingForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormBuilding);
            resultView.setMsg("(保存/修改)宿舍楼信息成功");
            logger.info("(保存/修改)宿舍楼信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)宿舍楼信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)宿舍楼失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍楼信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDormBuilding",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDormBuilding（删除宿舍楼信息）",notes = "删除宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormBuilding(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍楼接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            buildingService.deleteDormBuilding(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍楼成功");
            logger.info("删除宿舍楼成功");
        }catch (Exception e){
            logger.error("调用删除宿舍楼接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDormBuildings",method= RequestMethod.POST)
    @ApiOperation(value = "listDormBuildings（分页查询宿舍楼信息）",notes = "分页查询宿舍楼信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBuildingView.class)
    })
    @ResponseBody
    public ResultView listDormBuildings(@RequestBody DormBuildingForm buildingForm){
        logger.info("开始调用分页查询宿舍楼信息接口："+buildingForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<DormBuildingView> pageInfo = buildingService.listDormBuildings(buildingForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍楼信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
