package com.waken.dorm.app.controller.student;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.annotation.PrivilegeResource;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.AccessStrategy;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.student.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AppStudentController
 * @Description AppStudentController
 * @Author zhaoRong
 * @Date 2019/3/29 21:26
 **/
@Api(value = "APP端学生相关接口", description = "APP端学生相关接口(赵荣)")
@RestController
public class AppStudentController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;
    @Autowired
    StudentManager studentManager;

    /**
     * 学生登录
     */
    @PrivilegeResource(strategy = AccessStrategy.Guest)
    @PostMapping(value = {"student/studentLogin"})
    @ApiOperation(value = "studentLogin（学生登录）", notes = "学生登录,密码默认与学号一样")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView studentLogin(@RequestParam Integer studentNum, String password) {
        logger.info("开始调用学生登陆接口：" + studentNum);
        ResultView resultView = new ResultView();
        if (studentNum == null) {
            logger.info("学号为空！");
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("学号为空！");
        }
        try {
            resultView = studentService.studentLogin(studentNum, password);
        } catch (Exception e) {
            logger.info("登录失败原因：" + e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("登录失败原因：" + e.getMessage());
        }
        return resultView;
    }

    /**
     * 学生第一次登陆必须先设置密码
     */
    @PrivilegeResource(strategy = AccessStrategy.Guest)
    @PostMapping(value = {"student/updatePasswordByNew"})
    @ApiOperation(value = "updatePasswordByNew（学生第一次登陆必须先设置密码）", notes = "学生第一次登陆必须先设置密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView updatePasswordByNew(@RequestParam String studentId, String newPassword) {
        logger.info("开始调用学生设置密码接口：" + studentId);
        ResultView resultView = new ResultView();
        try {
            studentService.updatePasswordByNew(studentId, newPassword);
        } catch (Exception e) {
            logger.info("学生设置密码原因：" + e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("学生设置密码原因：" + e.getMessage());
        }
        return resultView;
    }

    @PostMapping(value = "/student/uploadHeadImg")
    @ApiOperation(value = "uploadHeadImg（学生头像上传）", notes = "学生头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ResultView.class)})
    public ResultView uploadHeadImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        String studentId = studentManager.getCurrentStudentId();
        ResultView resultView = new ResultView();
        try {
            ImgView imgView = studentService.uploadHeadImg(file, studentId);
            resultView.setMsg("学生头像上传成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(imgView);
            logger.info("学生头像成功！");
        } catch (Exception e) {
            resultView.setMsg("学生头像上传失败:" + e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }

    /**
     * 注销登录
     *
     * @return
     */
    @PrivilegeResource(strategy = AccessStrategy.Guest)
    @GetMapping(value = "/student/loginout")
    @ApiOperation(value = "loginout（学生注销登录）", notes = "学生注销登录 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView loginOut() {
        logger.info("开始调用注销接口");
        studentManager.delCacheStudentInfo();
        return ResultUtil.success();
    }
}
