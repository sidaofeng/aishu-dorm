package com.waken.dorm.controller.basic;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.StudentBasicForm;
import com.waken.dorm.common.utils.FileUtils;
import com.waken.dorm.common.view.student.StudentBasicView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.student.StudentBasicService;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Description 学生基本信息管理
 * @Author zhaoRong
 * @Date 2019/3/29 21:24
 **/
@Slf4j
@Api(value = "学生基本信息管理", description = "基础信息管理-学生基本信息管理")
@RestController
public class BasicStudentController extends BaseController {
    @Autowired
    private StudentBasicService studentBasicService;

    /**
     * 新增
     *
     * @param basic
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "student/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody StudentBasic basic) {
        if (this.studentBasicService.insert(basic) == 1) {
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
    @DeleteMapping(value = "student/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.studentBasicService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param basic
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "student/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody StudentBasic basic) {
        if (this.studentBasicService.update(basic) == 1) {
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
    @GetMapping(value = "student/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = StudentBasic.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.studentBasicService.get(id));
    }


    @CrossOrigin
    @PostMapping(value = "student/page")
    @ApiOperation(value = "分页", notes = "分页")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = StudentBasicView.class)
    })
    public AjaxResponse page(@RequestBody StudentBasicForm basicForm) {

        return AjaxResponse.success(studentBasicService.findPage(basicForm));
    }
    /**
     * 导入批量学生（excel）
     */
    @Log("导入学生信息（excel）")
    @CrossOrigin
    @PostMapping("student/import")
    @ApiOperation(value = "导入学生信息（excel）", notes = "导入批量学生（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse batchAddStudent(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用导入批量学生接口：" + file.getOriginalFilename());
        try {
            FileUtils.checkFile(file);
            long beginMillis = System.currentTimeMillis();
            List<StudentBasic> successList = Lists.newArrayList();
            List<Map<String, Object>> errorList = Lists.newArrayList();
            ExcelKit.$Import(StudentBasic.class)
                    .readXlsx(file.getInputStream(), new ExcelReadHandler<StudentBasic>() {
                        @Override
                        public void onSuccess(int sheetIndex, int rowIndex, StudentBasic student) {
                            successList.add(student);
                        }

                        @Override
                        public void onError(int sheetIndex, int rowIndex, List<ExcelErrorField> list) {
                            errorList.add(ImmutableMap
                                    .of("sheetIndex", sheetIndex, "rowIndex", rowIndex, "list", list));
                        }
                    });
            long time = (System.currentTimeMillis() - beginMillis) / 1000L;
            if (!errorList.isEmpty()) {
                return AjaxResponse.error(ImmutableMap.of("timeConsuming", time, "errorList", errorList));
            }
            if (!successList.isEmpty()) {
                this.studentBasicService.batchInsert(successList);
            }
            return AjaxResponse.success(ImmutableMap.of("timeConsuming", time));
        } catch (Exception e) {
            log.info("导入批量学生失败原因：" + e.getMessage());
            return AjaxResponse.error("导入批量学生失败");
        }
    }

    // 生成导入模板
    @GetMapping(value = "student/export/template")
    public AjaxResponse exportTemplate(HttpServletResponse response) {
        List<StudentBasic> studentBasicList = new ArrayList<>();
        ExcelKit.$Export(StudentBasic.class, response).downXlsx(studentBasicList, true);
        return AjaxResponse.success();
    }

    @GetMapping(value = "student/export")
    public AjaxResponse export(HttpServletResponse response) {
        long beginMillis = System.currentTimeMillis();
        List<StudentBasic> studentBasicList = this.studentBasicService.list();
        ExcelKit.$Export(StudentBasic.class, response).downXlsx(studentBasicList, false);
        long time = (System.currentTimeMillis() - beginMillis) / 1000L;
        return AjaxResponse.success(ImmutableMap.of("timeConsuming", time));
    }
}
