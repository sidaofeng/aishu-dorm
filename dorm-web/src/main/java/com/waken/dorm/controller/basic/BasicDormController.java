package com.waken.dorm.controller.basic;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.FloorDormForm;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.basic.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormController
 * @Description 宿舍"
 * @Author zhaoRong
 * @Date 2019/3/31 13:36
 **/
@Slf4j
@Api(value = "宿舍房间管理", description = "基础信息管理-宿舍房间管理")
@RestController
public class BasicDormController extends BaseController {
    @Autowired
    private DormService dormService;

    /**
     * 新增
     *
     * @param dorm
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody Dorm dorm) {
        if (this.dormService.insert(dorm) == 1) {
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
    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.dormService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param dorm
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody Dorm dorm) {
        if (this.dormService.update(dorm) == 1) {
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
    @GetMapping(value = "{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dorm.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.dormService.get(id));
    }


    @CrossOrigin
    @PostMapping(value = "page")
    @ApiOperation(value = "分页", notes = "分页")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormView.class)
    })
    public AjaxResponse page(@RequestBody DormForm dormForm) {
        log.info("开始调用分页查询宿舍信息接口：" + dormForm.toString());
        return AjaxResponse.success(dormService.page(dormForm));
    }

    /**
     * 通过校区获取建筑信息
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "list/{floorId}")
    @ApiOperation(value = "通过楼层Id获取宿舍信息", notes = "通过楼层Id获取宿舍信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Building.class)
    })
    public AjaxResponse listByFloor(@PathVariable("floorId") String floorId) {
        return AjaxResponse.success(this.dormService.listByFloor(floorId));
    }

    /**
     * 通过楼层id自动生成宿舍房间、床位
     * 默认生成规则：建筑号+"-"+楼层号+流水号
     *
     * @param form
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "batch/add")
    @ApiOperation(value = "通过楼层id自动生成宿舍房间、床位", notes = "通过楼层id自动生成宿舍房间、床位，" +
            "默认生成规则：建筑号+\"-\"+楼层号+流水号")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse batchInsert(@RequestBody FloorDormForm form) {
        if (this.dormService.batchInsert(form) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }
    }
}
