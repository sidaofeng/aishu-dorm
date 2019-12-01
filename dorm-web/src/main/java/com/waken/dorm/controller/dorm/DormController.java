package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
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
     * 通过id获取楼层信息
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
