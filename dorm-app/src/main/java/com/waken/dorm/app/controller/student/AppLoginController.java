package com.waken.dorm.app.controller.student;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.annotation.PrivilegeResource;
import com.waken.dorm.common.enums.AccessStrategy;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.student.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AppLoginController
 * @Description AppLoginController
 * @Author zhaoRong
 * @Date 2019/3/29 21:26
 **/
@Slf4j
@Api(value = "APP端登陆相关接口", description = "APP端登陆相关接口(赵荣)")
@RestController
public class AppLoginController extends AppBaseController {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentManager studentManager;

    /**
     * 学生登录
     */
    @PrivilegeResource(strategy = AccessStrategy.Anon)
    @PostMapping(value = {"student/studentLogin"})
    @ApiOperation(value = "studentLogin（学生登录）", notes = "学生登录,密码默认与学号一样"+
            "<br/>请求参数JSON示例:{\"studentNum\":\"20161545\",\"password\":\"123456\"}" +
            "<br/>必填参数：studentNum（学号） " +
            "<br/>必填参数：password（密码） " +
            "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse studentLogin(@RequestParam Integer studentNum, String password) {
        log.info("开始调用学生登陆接口：" + studentNum);
        if (null == studentNum || StringUtils.isBlank(password)) {
            return AjaxResponse.error("学号或密码不能为空！");
        }
        return studentService.studentLogin(studentNum, password);
    }

    /**
     * 学生第一次登陆必须先设置密码
     */
    @PostMapping(value = {"student/updatePasswordByNew"})
    @ApiOperation(value = "updatePasswordByNew（学生第一次登陆必须先设置密码）", notes = "学生第一次登陆必须先设置密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse updatePasswordByNew(@RequestParam String newPassword) {
        log.info("开始调用学生设置密码接口：");
        studentService.updatePasswordByNew(newPassword);
        return AjaxResponse.success();
    }

    @PostMapping(value = "/student/uploadHeadImg")
    @ApiOperation(value = "uploadHeadImg（学生头像上传）", notes = "学生头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    public AjaxResponse uploadHeadImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        String studentId = studentManager.getCurrentStudentId();
        ImgView imgView = studentService.uploadHeadImg(file, studentId);
        return AjaxResponse.success(imgView);
    }

    /**
     * 注销登录
     *
     * @return
     */
    @PrivilegeResource(strategy = AccessStrategy.Guest)
    @GetMapping(value = "/student/logout")
    @ApiOperation(value = "logout（学生注销登录）", notes = "学生注销登录 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse loginOut() {
        log.info("开始调用注销接口");
        studentManager.delCacheStudentInfo();
        return AjaxResponse.success();
    }
}
