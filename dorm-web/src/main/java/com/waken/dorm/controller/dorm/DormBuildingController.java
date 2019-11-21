package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.form.dorm.EditBuildingForm;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormBuildingController
 * @Description 宿舍楼模块相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Slf4j
@Api(value = "宿舍楼模块相关接口", description = "宿舍楼模块相关接口(AiShu)")
@RestController
public class DormBuildingController extends BaseController {
    @Autowired
    private DormBuildingService buildingService;

    @Log("(保存/修改)宿舍楼信息")
    @CrossOrigin
    @PostMapping(value = "building/save")
    @ApiOperation(value = "(保存/修改)宿舍楼信息", notes = "(保存/修改)宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Building.class)
    })
    public AjaxResponse saveDormBuilding(@RequestBody EditBuildingForm editBuildingForm) {
        log.info("开始调用(保存/修改)宿舍楼信息接口接口：" + editBuildingForm.toString());
        Building building = buildingService.saveDormBuilding(editBuildingForm);
        return AjaxResponse.success(building);
    }

    @Log("删除宿舍楼信息")
    @CrossOrigin
    @DeleteMapping(value = "building/delete")
    @ApiOperation(value = "删除宿舍楼信息", notes = "删除宿舍楼信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDormBuilding(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍楼接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        buildingService.deleteDormBuilding(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "building/page")
    @ApiOperation(value = "分页查询宿舍楼信息", notes = "分页查询宿舍楼信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBuildingView.class)
    })
    public AjaxResponse listDormBuildings(@RequestBody BuildingForm buildingForm) {
        log.info("开始调用分页查询宿舍楼信息接口：" + buildingForm.toString());
        return AjaxResponse.success(buildingService.listDormBuildings(buildingForm));
    }
}
