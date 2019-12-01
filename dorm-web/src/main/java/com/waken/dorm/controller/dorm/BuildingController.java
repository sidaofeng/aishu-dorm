package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.view.dorm.BuildingView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BuildingController
 * @Description 建筑模块相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Slf4j
@Api(value = "建筑模块相关接口", description = "建筑模块相关接口(AiShu)")
@RestController
public class BuildingController extends BaseController {
    @Autowired
    private BuildingService buildingService;

    /**
     * 新增
     *
     * @param building
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "building/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody Building building) {
        if (this.buildingService.insert(building) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }
    }

    /**
     * 删除
     * @param deleteForm
     */
    @CrossOrigin
    @DeleteMapping(value = "building/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.buildingService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param building
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "building/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody Building building) {
        if (this.buildingService.update(building) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取建筑信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "building/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Building.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.buildingService.get(id));
    }

    /**
     * 集合
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "building/list/{campusId}")
    @ApiOperation(value = "集合", notes = "集合 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Building.class)
    })
    public AjaxResponse list(@PathVariable("campusId") String campusId) {
        return AjaxResponse.success(this.buildingService.list(campusId));
    }

    /**
     * 分页
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "building/page")
    @ApiOperation(value = "分页", notes = "分页 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BuildingView.class)
    })
    public AjaxResponse page(@RequestBody BuildingForm form) {
        return AjaxResponse.success(this.buildingService.page(form));
    }
}
