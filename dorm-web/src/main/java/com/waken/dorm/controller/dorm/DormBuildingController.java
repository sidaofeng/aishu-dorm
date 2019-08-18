package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.utils.ResultUtil;
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
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormBuildingController
 * @Description 宿舍楼模块相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Api(value = "宿舍楼模块相关接口", description = "宿舍楼模块相关接口(AiShu)")
@RestController
public class DormBuildingController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DormBuildingService buildingService;

    @Log("(保存/修改)宿舍楼信息")
    @CrossOrigin
    @PostMapping(value = "building/save")
    @ApiOperation(value = "(保存/修改)宿舍楼信息", notes = "(保存/修改)宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBuilding.class)
    })
    public ResultView saveDormBuilding(@RequestBody EditDormBuildingForm editDormBuildingForm) {
        logger.info("开始调用(保存/修改)宿舍楼信息接口接口：" + editDormBuildingForm.toString());
        try {
            DormBuilding dormBuilding = buildingService.saveDormBuilding(editDormBuildingForm);
            return ResultUtil.success(dormBuilding);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用(保存/修改)宿舍楼信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除宿舍楼信息")
    @CrossOrigin
    @DeleteMapping(value = "building/delete")
    @ApiOperation(value = "删除宿舍楼信息", notes = "删除宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteDormBuilding(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用删除宿舍楼接口：" + deleteFrom.toString());
        try {
            buildingService.deleteDormBuilding(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用删除宿舍楼接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "building/page")
    @ApiOperation(value = "分页查询宿舍楼信息", notes = "分页查询宿舍楼信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBuildingView.class)
    })
    public ResultView listDormBuildings(DormBuildingForm buildingForm) {
        logger.info("开始调用分页查询宿舍楼信息接口：" + buildingForm.toString());
        try {
            PageInfo<DormBuildingView> pageInfo = buildingService.listDormBuildings(buildingForm);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            logger.error("调用分页查询宿舍楼信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
