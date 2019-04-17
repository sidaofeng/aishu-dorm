package com.waken.dorm.controller.student;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.view.student.StudentView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.student.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/29 21:24
 **/
@Api(value = "后台管理学生相关接口", description = "后台管理学生相关接口(赵荣)")
@Controller
public class StudentController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;
    /**
     * 导入批量学生（excel）
     */
    @Log("导入批量学生（excel）")
    @CrossOrigin
    @RequestMapping(value = {"/student/batchAddStudent"},method = RequestMethod.POST)
    @ApiOperation(value = "batchAddStudent（导入批量学生（excel））",notes = "导入批量学生（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView batchAddStudent(@RequestParam(value = "file", required = false) MultipartFile file){
        logger.info("开始调用导入批量学生接口："+file.getOriginalFilename().toString());
        ResultView resultView = new ResultView();
        try{
            studentService.batchAddStudent(file);
            logger.info("导入批量学生成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("导入批量学生成功");
        }catch (Exception e){
            logger.info("导入批量学生失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("导入批量学生失败原因："+e.getMessage());
        }
        return resultView;
    }
    @Log("(保存/修改)单个学生信息")
    @CrossOrigin
    @RequestMapping(value= "/student/saveStudent",method= RequestMethod.POST)
    @ApiOperation(value = "saveStudent（(保存/修改)单个学生信息）",notes = "(保存/修改)单个学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Student.class)
    })
    @ResponseBody
    public ResultView saveStudent(@RequestBody EditStudentForm editStudentForm){
        logger.info("开始调用(保存/修改)单个学生信息接口接口："+editStudentForm.toString());
        ResultView resultView = new ResultView();
        try{
            Student Student = studentService.saveStudent(editStudentForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(Student);
            resultView.setMsg("(保存/修改)单个学生信息成功");
            logger.info("(保存/修改)单个学生信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)单个学生信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)单个学生失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除学生信息")
    @CrossOrigin
    @RequestMapping(value= "/student/deleteStudent",method= RequestMethod.POST)
    @ApiOperation(value = "deleteStudent（删除学生信息）",notes = "删除学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteStudent(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除学生接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            studentService.deleteStudent(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除学生成功");
            logger.info("删除学生成功");
        }catch (Exception e){
            logger.error("调用删除学生接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/student/listStudents",method= RequestMethod.POST)
    @ApiOperation(value = "listStudents（分页查询学生信息）",notes = "分页查询学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = StudentView.class)
    })
    @ResponseBody
    public ResultView listStudents(@RequestBody StudentForm studentForm){
        logger.info("开始调用分页查询学生信息接口："+studentForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<StudentView> pageInfo = studentService.listStudents(studentForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询学生信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
