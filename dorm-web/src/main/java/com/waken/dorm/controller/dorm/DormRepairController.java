package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormRepairController
 * @Description 后台管理宿舍维修
 * @Author zhaoRong
 * @Date 2019/4/2 9:57
 **/
@Slf4j
@Api(value = "宿舍维修管理", description = "宿舍管理-宿舍维修管理")
@RestController
public class DormRepairController extends BaseController {
    @Autowired
    private RepairService repairService;

    /**
     * 新增
     *
     * @param repair
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody DormRepair repair) {
        if (this.repairService.insert(repair) == 1) {
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
    @DeleteMapping(value = "repair/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.repairService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param repair
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "repair/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody DormRepair repair) {
        if (this.repairService.update(repair) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取维修信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "repair/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepair.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.repairService.get(id));
    }


    @CrossOrigin
    @PostMapping(value = "repair/page")
    @ApiOperation(value = "分页", notes = "分页 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public AjaxResponse findPage(@RequestBody DormRepairForm dormRepairForm) {
        log.info("开始调用分页查询宿舍维修信息接口：" + dormRepairForm.toString());
        return AjaxResponse.success(repairService.findPage(dormRepairForm));
    }
}
