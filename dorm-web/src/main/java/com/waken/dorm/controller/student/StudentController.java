package com.waken.dorm.controller.student;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.utils.FileUtils;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.student.StudentView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.student.StudentService;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/29 21:24
 **/
@Api(value = "后台管理学生相关接口", description = "后台管理学生相关接口(AiShu)")
@RestController
public class StudentController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    /**
     * 导入批量学生（excel）
     */
    @Log("导入学生信息（excel）")
    @CrossOrigin
    @PostMapping(value = {"student/import"})
    @ApiOperation(value = "导入学生信息（excel）", notes = "导入批量学生（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddStudent(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("开始调用导入批量学生接口：" + file.getOriginalFilename());
        try {
            FileUtils.checkFile(file);
            long beginMillis = System.currentTimeMillis();
            List<Student> successList = Lists.newArrayList();
            List<Map<String, Object>> errorList = Lists.newArrayList();
            ExcelKit.$Import(DormScore.class)
                    .readXlsx(file.getInputStream(), new ExcelReadHandler<Student>() {
                        @Override
                        public void onSuccess(int sheetIndex, int rowIndex, Student student) {
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
                return ResultUtil.error(ImmutableMap.of("timeConsuming", time, "errorList", errorList));
            }
            if (!successList.isEmpty()) {
                studentService.batchAddStudent(successList);
            }
            return ResultUtil.success(ImmutableMap.of("timeConsuming", time));
        } catch (Exception e) {
            logger.info("导入批量学生失败原因：" + e.getMessage());
            return ResultUtil.errorByMsg("导入批量学生失败");
        }
    }

    @Log("(保存/修改)单个学生信息")
    @CrossOrigin
    @PostMapping(value = "student/save")
    @ApiOperation(value = "saveStudent（(保存/修改)单个学生信息）", notes = "(保存/修改)单个学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Student.class)
    })
    public ResultView saveStudent(@RequestBody EditStudentForm editStudentForm) {
        logger.info("开始调用(保存/修改)单个学生信息接口接口：" + editStudentForm.toString());
        try {
            Student student = studentService.saveStudent(editStudentForm);
            return ResultUtil.success(student);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用(保存/修改)单个学生信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除学生信息")
    @CrossOrigin
    @DeleteMapping(value = "student/delete")
    @ApiOperation(value = "删除学生信息", notes = "删除学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteStudent(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用删除学生接口：" + deleteFrom.toString());
        try {
            studentService.deleteStudent(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用删除学生接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "student/page")
    @ApiOperation(value = "分页查询学生信息", notes = "分页查询学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = StudentView.class)
    })
    public ResultView listStudents(StudentForm studentForm) {
        logger.info("开始调用分页查询学生信息接口：" + studentForm.toString());
        try {
            PageInfo<StudentView> pageInfo = studentService.listStudents(studentForm);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            logger.error("调用分页查询学生信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
