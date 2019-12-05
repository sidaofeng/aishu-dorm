package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
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
 * @Description 宿舍违规
 * @Author zhaoRong
 * @Date 2019/4/2 13:39
 **/
@Slf4j
@Api(value = "宿舍违规管理", description = "宿舍管理-宿舍违规管理")
@RestController
public class DormViolationController extends BaseController {
    @Autowired
    private DormViolationService violationService;

    /**
     * 新增
     *
     * @param violation
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "violation/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody DormViolation violation) {
        if (this.violationService.insert(violation) == 1) {
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
    @DeleteMapping(value = "violation/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.violationService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param violation
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "violation/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody DormViolation violation) {
        if (this.violationService.update(violation) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取违规信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "violation/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolation.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.violationService.get(id));
    }


    @CrossOrigin
    @PostMapping(value = "violation/page")
    @ApiOperation(value = "分页", notes = "分页")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormViolationView.class)
    })
    public AjaxResponse page(@RequestBody DormViolationForm dormViolationForm) {
        return AjaxResponse.success(this.violationService.page(dormViolationForm));
    }

}
