package com.waken.dorm.controller.basic;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.SchoolCampus;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.basic.SchoolCampusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SchoolCampusController
 * @Description 校区模块
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Slf4j
@Api(value = "校区管理", description = "基础信息管理-校区管理")
@RestController
public class BasicSchoolCampusController extends BaseController {
    @Autowired
    private SchoolCampusService schoolCampusService;

    /**
     * 新增
     *
     * @param schoolCampus
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "campus/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("campus::add")
    public AjaxResponse insert(@RequestBody SchoolCampus schoolCampus) {
        if (this.schoolCampusService.insert(schoolCampus) == 1) {
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
    @DeleteMapping(value = "campus/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("campus::delete")
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.schoolCampusService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param schoolCampus
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "campus/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    //@RequiresPermissions("campus::update")
    public AjaxResponse update(@RequestBody SchoolCampus schoolCampus) {
        if (this.schoolCampusService.update(schoolCampus) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取校区信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "campus/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolCampus.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.schoolCampusService.get(id));
    }

    /**
     * 查询校区集合
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "campus/list")
    @ApiOperation(value = "查询校区集合", notes = "查询校区集合 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolCampus.class)
    })
    //@RequiresPermissions("campus::list")
    public AjaxResponse list() {
        return AjaxResponse.success(this.schoolCampusService.list());
    }
}
