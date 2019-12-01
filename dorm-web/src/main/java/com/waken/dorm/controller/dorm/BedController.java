package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.BedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName bedController
 * @Description 宿舍床位模块相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 12:35
 **/
@Slf4j
@Api(value = "宿舍床位相关接口", description = "宿舍床位模块相关接口(AiShu)")
@RestController
public class BedController extends BaseController {
    @Autowired
    private BedService bedService;

    /**
     * 新增
     *
     * @param bed
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "bed/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody DormBed bed) {
        if (this.bedService.insert(bed) == 1) {
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
    @DeleteMapping(value = "bed/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.bedService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param bed
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "bed/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody DormBed bed) {
        if (this.bedService.update(bed) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取宿舍床位信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "bed/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBed.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.bedService.get(id));
    }

    /**
     * 查询宿舍床位集合
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "bed/list/{dormId}")
    @ApiOperation(value = "通过宿舍ID查询床位集合", notes = "通过宿舍ID查询床位集合 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormBed.class)
    })
    public AjaxResponse list(@PathVariable("dormId") String dormId) {
        return AjaxResponse.success(this.bedService.list(dormId));
    }
}
