package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
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
 * @Description 后台管理宿舍维修相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 9:57
 **/
@Slf4j
@Api(value = "后台管理宿舍维修相关接口", description = "后台管理宿舍维修相关接口(AiShu)")
@RestController
public class DormRepairController extends BaseController {
    @Autowired
    RepairService repairService;

    @Log("新增宿舍维修记录")
    @CrossOrigin
    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增宿舍维修记录", notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepair.class)
    })
    public AjaxResponse addDormRepair(@RequestBody AddDormRepairForm addDormRepairForm) {
        log.info("开始调用新增宿舍维修记录接口：" + addDormRepairForm.toString());
        addDormRepairForm.setTerminal(CodeEnum.WEB.getCode());
        return AjaxResponse.success(repairService.addDormRepair(addDormRepairForm));
    }

    @Log("删除宿舍维修信息")
    @CrossOrigin
    @DeleteMapping(value = "repair/delete")
    @ApiOperation(value = "删除宿舍维修信息", notes = "删除宿舍维修信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDormRepair(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍维修信息接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        repairService.deleteDormRepair(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "repair/page")
    @ApiOperation(value = "分页查询宿舍维修信息", notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public AjaxResponse listDormRepairs(@RequestBody DormRepairForm dormRepairForm) {
        log.info("开始调用分页查询宿舍维修信息接口：" + dormRepairForm.toString());
        dormRepairForm.setTerminal(CodeEnum.WEB.getCode());
        return AjaxResponse.success(repairService.listDormRepairs(dormRepairForm));
    }

    @Log("修改宿舍维修")
    @CrossOrigin
    @PutMapping(value = "repair/update")
    @ApiOperation(value = "修改宿舍维修", notes = "修改宿舍维修 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormRepair.class)})
    public AjaxResponse updateDormRepair(@RequestBody UpdateRepairForm updateRepairForm) {
        log.info("开始调用修改宿舍维修接口：" + updateRepairForm.toString());
        DormRepair dormRepair = repairService.updateDormRepair(updateRepairForm);
        return AjaxResponse.success(dormRepair);
    }
}
