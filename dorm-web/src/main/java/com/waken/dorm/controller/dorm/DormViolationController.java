package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormViolationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormViolationController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 13:39
 **/
@Api(value = "后台管理宿舍违规相关接口", description = "后台管理宿舍违规相关接口(AiShu)")
@RestController
public class DormViolationController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormViolationService dormViolationService;

    @Log("新增宿舍违规记录")
    @CrossOrigin
    @PostMapping(value = {"violation/add"})
    @ApiOperation(value = "新增宿舍违规记录", notes = "新增宿舍违规记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolation.class)
    })
    public ResultView addDormViolation(@RequestBody AddDormViolationForm addDormViolationForm) {
        logger.info("开始调用新增宿舍违规记录接口：" + addDormViolationForm.toString());
        try {
            return ResultUtil.success(dormViolationService.addDormViolation(addDormViolationForm));
        } catch (Exception e) {
            logger.info("新增宿舍违规记录失败原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除宿舍违规信息")
    @CrossOrigin
    @RequestMapping(value = "violation/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除宿舍违规信息", notes = "删除宿舍违规信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormViolation(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用删除宿舍违规信息接口：" + deleteFrom.toString());
        try {
            dormViolationService.deleteDormViolation(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用删除宿舍违规信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @PostMapping(value = "violation/page")
    @ApiOperation(value = "listDormViolations（分页查询宿舍违规信息）", notes = "分页查询宿舍违规信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolationView.class)
    })
    public ResultView listDormViolations(@RequestBody DormViolationForm dormViolationForm) {
        logger.info("开始调用分页查询宿舍违规信息接口：" + dormViolationForm.toString());
        try {
            return ResultUtil.success(dormViolationService.listDormViolations(dormViolationForm));
        } catch (Exception e) {
            logger.error("调用分页查询宿舍违规信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("修改宿舍违规")
    @CrossOrigin
    @PutMapping(value = "violation/update")
    @ApiOperation(value = "修改宿舍违规", notes = "修改宿舍违规 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormViolation.class)})
    public ResultView updateDormViolation(@RequestBody UpdateDormViolationForm updateViolationForm) {
        logger.info("开始调用修改宿舍违规接口：" + updateViolationForm.toString());
        try {
            return ResultUtil.success(dormViolationService.updateDormViolation(updateViolationForm));
        } catch (Exception e) {
            logger.error("修改宿舍违规失败，原因 :" + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

}
