package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormController
 * @Description 宿舍相关接口"
 * @Author zhaoRong
 * @Date 2019/3/31 13:36
 **/
@Api(value = "宿舍模块相关接口", description = "宿舍模块相关接口(AiShu)")
@RestController
public class DormController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DormService dormService;

    @Log("(保存/修改)宿舍信息")
    @CrossOrigin
    @PostMapping(value = "add")
    @ApiOperation(value = "(保存/修改)宿舍信息", notes = "(保存/修改)宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dorm.class)
    })
    public ResultView saveDorm(@RequestBody EditDormForm editDormForm) {
        logger.info("开始调用(保存/修改)宿舍信息接口接口：" + editDormForm.toString());
        try {
            Dorm dorm = dormService.saveDorm(editDormForm);
            return ResultUtil.success(dorm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用(保存/修改)宿舍信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除宿舍信息")
    @CrossOrigin
    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除宿舍信息", notes = "删除宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteDorm(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用删除宿舍接口：" + deleteFrom.toString());
        try {
            dormService.deleteDorm(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用删除宿舍接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @PostMapping(value = "page")
    @ApiOperation(value = "分页查询宿舍信息", notes = "分页查询宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormView.class)
    })
    public ResultView listDorms(@RequestBody DormForm dormForm) {
        logger.info("开始调用分页查询宿舍信息接口：" + dormForm.toString());
        try {
            PageInfo<DormView> pageInfo = dormService.listDorms(dormForm);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            logger.error("调用分页查询宿舍信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "students/dorms/{id}")
    @ApiOperation(value = "查询宿舍与学生（关联与未关联）信息", notes = "查询宿舍与学生（关联与未关联）信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormStudentsView.class)
    })
    public ResultView queryDormStudentsView(@PathVariable String id) {
        logger.info("开始调用查询宿舍与学生关联信息接口：" + id);
        try {
            DormStudentsView dormStudentsView = dormService.queryDormStudentsView(id);
            return ResultUtil.success(dormStudentsView);
        } catch (Exception e) {
            logger.error("调用查询宿舍与学生关联信息接口失败:", e);
            return ResultUtil.error();
        }
    }

    @Log("批量添加宿舍学生关联")
    @CrossOrigin
    @PostMapping(value = "students-dorms/batch/add")
    @ApiOperation(value = "批量添加宿舍学生关联", notes = "批量添加宿舍学生关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddDormStudentRel(@RequestBody AddDormStudentRelForm addDormStudentRelForm) {
        logger.info("开始调用批量添加宿舍学生关联接口：" + addDormStudentRelForm.toString());
        try {
            dormService.batchAddDormStudentRel(addDormStudentRelForm);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用批量添加宿舍学生关联接口失败，原因:" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
