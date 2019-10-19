package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormController
 * @Description 宿舍相关接口"
 * @Author zhaoRong
 * @Date 2019/3/31 13:36
 **/
@Slf4j
@Api(value = "宿舍模块相关接口", description = "宿舍模块相关接口(AiShu)")
@RestController
public class DormController extends BaseController {
    @Autowired
    private DormService dormService;

    @Log("(保存/修改)宿舍信息")
    @CrossOrigin
    @PostMapping(value = "add")
    @ApiOperation(value = "(保存/修改)宿舍信息", notes = "(保存/修改)宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dorm.class)
    })
    public AjaxResponse saveDorm(@RequestBody EditDormForm editDormForm) {
        log.info("开始调用(保存/修改)宿舍信息接口接口：" + editDormForm.toString());
        Dorm dorm = dormService.saveDorm(editDormForm);
        return AjaxResponse.success(dorm);
    }

    @Log("删除宿舍信息")
    @CrossOrigin
    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除宿舍信息", notes = "删除宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDorm(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        dormService.deleteDorm(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "page")
    @ApiOperation(value = "分页查询宿舍信息", notes = "分页查询宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormView.class)
    })
    public AjaxResponse listDorms(@RequestBody DormForm dormForm) {
        log.info("开始调用分页查询宿舍信息接口：" + dormForm.toString());
        return AjaxResponse.success(dormService.listDorms(dormForm));
    }

    @CrossOrigin
    @GetMapping(value = "students/dorms/{id}")
    @ApiOperation(value = "查询宿舍与学生（关联与未关联）信息", notes = "查询宿舍与学生（关联与未关联）信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormStudentsView.class)
    })
    public AjaxResponse queryDormStudentsView(@PathVariable String id) {
        log.info("开始调用查询宿舍与学生关联信息接口：" + id);
        DormStudentsView dormStudentsView = dormService.queryDormStudentsView(id);
        return AjaxResponse.success(dormStudentsView);
    }

    @Log("批量添加宿舍学生关联")
    @CrossOrigin
    @PostMapping(value = "students-dorms/batch/add")
    @ApiOperation(value = "批量添加宿舍学生关联", notes = "批量添加宿舍学生关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse batchAddDormStudentRel(@RequestBody AddDormStudentRelForm addForm) {
        log.info("开始调用批量添加宿舍学生关联接口：" + addForm.toString());
        if (StringUtils.isEmpty(addForm.getDormId()) || null == addForm.getStudentIds() || addForm.getStudentIds().isEmpty()) {
            return AjaxResponse.error("参数为空");
        }
        dormService.batchAddDormStudentRel(addForm);
        return AjaxResponse.success();
    }
}
