package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormViolationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormViolationController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 13:39
 **/
@Slf4j
@Api(value = "后台管理宿舍违规相关接口", description = "后台管理宿舍违规相关接口(AiShu)")
@RestController
public class DormViolationController extends BaseController {
    @Autowired
    DormViolationService dormViolationService;

    @Log("新增宿舍违规记录")
    @CrossOrigin
    @PostMapping(value = {"violation/add"})
    @ApiOperation(value = "新增宿舍违规记录", notes = "新增宿舍违规记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolation.class)
    })
    public AjaxResponse addDormViolation(@RequestBody AddDormViolationForm addDormViolationForm) {
        log.info("开始调用新增宿舍违规记录接口：" + addDormViolationForm.toString());
        return AjaxResponse.success(dormViolationService.addDormViolation(addDormViolationForm));
    }

    @Log("删除宿舍违规信息")
    @CrossOrigin
    @RequestMapping(value = "violation/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除宿舍违规信息", notes = "删除宿舍违规信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @ResponseBody
    public AjaxResponse deleteDormViolation(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍违规信息接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        dormViolationService.deleteDormViolation(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "violation/page")
    @ApiOperation(value = "listDormViolations（分页查询宿舍违规信息）", notes = "分页查询宿舍违规信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolationView.class)
    })
    public AjaxResponse listDormViolations(@RequestBody DormViolationForm dormViolationForm) {
        log.info("开始调用分页查询宿舍违规信息接口：" + dormViolationForm.toString());
        return AjaxResponse.success(dormViolationService.listDormViolations(dormViolationForm));
    }

    @Log("修改宿舍违规")
    @CrossOrigin
    @PutMapping(value = "violation/update")
    @ApiOperation(value = "修改宿舍违规", notes = "修改宿舍违规 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormViolation.class)})
    public AjaxResponse updateDormViolation(@RequestBody UpdateDormViolationForm updateViolationForm) {
        log.info("开始调用修改宿舍违规接口：" + updateViolationForm.toString());
        return AjaxResponse.success(dormViolationService.updateDormViolation(updateViolationForm));
    }

}
