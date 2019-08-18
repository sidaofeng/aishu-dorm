package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormRepairService;
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
    DormRepairService dormRepairService;

    @Log("新增宿舍维修记录")
    @CrossOrigin
    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增宿舍维修记录", notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepair.class)
    })
    public ResultView addDormRepair(@RequestBody AddDormRepairForm addDormRepairForm) {
        log.info("开始调用新增宿舍维修记录接口：" + addDormRepairForm.toString());
        try {
            addDormRepairForm.setTerminal(CodeEnum.WEB.getCode());
            return ResultUtil.success(dormRepairService.addDormRepair(addDormRepairForm));
        } catch (Exception e) {
            log.info("新增宿舍维修记录失败原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除宿舍维修信息")
    @CrossOrigin
    @DeleteMapping(value = "repair/delete")
    @ApiOperation(value = "删除宿舍维修信息", notes = "删除宿舍维修信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteDormRepair(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍维修信息接口：" + deleteFrom.toString());
        try {
            dormRepairService.deleteDormRepair(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            log.error("调用删除宿舍维修信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "repair/page")
    @ApiOperation(value = "分页查询宿舍维修信息", notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public ResultView listDormRepairs(DormRepairForm dormRepairForm) {
        log.info("开始调用分页查询宿舍维修信息接口：" + dormRepairForm.toString());
        try {
            dormRepairForm.setTerminal(CodeEnum.WEB.getCode());
            PageInfo<DormRepairView> pageInfo = dormRepairService.listDormRepairs(dormRepairForm);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            log.error("调用分页查询宿舍维修信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("修改宿舍维修")
    @CrossOrigin
    @PutMapping(value = "repair/update")
    @ApiOperation(value = "修改宿舍维修", notes = "修改宿舍维修 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormRepair.class)})
    public ResultView updateDormRepair(@RequestBody UpdateRepairForm updateRepairForm) {
        log.info("开始调用修改宿舍维修接口：" + updateRepairForm.toString());
        try {
            DormRepair dormRepair = dormRepairService.updateDormRepair(updateRepairForm);
            return ResultUtil.success(dormRepair);
        } catch (Exception e) {
            log.error("修改宿舍维修失败，原因 :" + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error();
        }
    }
}
