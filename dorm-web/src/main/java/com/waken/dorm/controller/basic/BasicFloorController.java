package com.waken.dorm.controller.basic;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.BuildingFloor;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.basic.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FloorController
 * @Description 楼层模块
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Slf4j
@Api(value = "楼层", description = "基础信息管理-楼层")
@RestController
public class BasicFloorController extends BaseController {
    @Autowired
    private FloorService floorService;

    /**
     * 新增
     *
     * @param floor
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "floor/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("floor::add")
    public AjaxResponse insert(@RequestBody BuildingFloor floor) {
        if (this.floorService.insert(floor) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }
    }

    /**
     * 删除
     *
     * @param deleteForm
     */
    @CrossOrigin
    @DeleteMapping(value = "floor/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("floor::delete")
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.floorService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param floor
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "floor/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("floor::update")
    public AjaxResponse update(@RequestBody BuildingFloor floor) {
        if (this.floorService.update(floor) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "floor/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BuildingFloor.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.floorService.get(id));
    }

    /**
     * 查询楼层集合
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "floor/list/{buildId}")
    @ApiOperation(value = "通过建筑查询楼层集合", notes = "通过建筑查询楼层集合 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BuildingFloor.class)
    })
    //@RequiresPermissions("floor::list")
    public AjaxResponse list(@PathVariable("buildId") String buildId) {
        return AjaxResponse.success(this.floorService.list(buildId));
    }
}
