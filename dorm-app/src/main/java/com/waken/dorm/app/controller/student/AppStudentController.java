package com.waken.dorm.app.controller.student;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.utils.redis.RedisUtils;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.service.student.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    RedisUtils redisUtils;
    /**
     * 学生登录
     */
    @RequestMapping(value = {"student/studentLogin"},method = RequestMethod.POST)
    @ApiOperation(value = "studentLogin（学生登录）",notes = "学生登录,密码默认与学号一样")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView studentLogin(@RequestParam Integer studentNum, String password){
        logger.info("开始调用学生登陆接口："+studentNum);
        ResultView resultView = new ResultView();
        if (studentNum == null){
            logger.info("学号为空！");
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("学号为空！");
        }
        try{
            resultView = studentService.studentLogin(studentNum,password);
        }catch (Exception e){
            logger.info("登录失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("登录失败原因："+e.getMessage());
        }
        return resultView;
    }

    /**
     * 学生第一次登陆必须先设置密码
     */
    @RequestMapping(value = {"student/updatePasswordByNew"},method = RequestMethod.POST)
    @ApiOperation(value = "updatePasswordByNew（学生第一次登陆必须先设置密码）",notes = "学生第一次登陆必须先设置密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView updatePasswordByNew(@RequestParam String studentId,String newPassword){
        logger.info("开始调用学生设置密码接口："+studentId);
        ResultView resultView = new ResultView();
        try{
            studentService.updatePasswordByNew(studentId,newPassword);
        }catch (Exception e){
            logger.info("学生设置密码原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("学生设置密码原因："+e.getMessage());
        }
        return resultView;
    }
    @RequestMapping(value = "/student/uploadHeadImg", method = RequestMethod.POST)
    @ApiOperation(value = "uploadHeadImg（学生头像上传）", notes = "学生头像上传")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = ResultView.class) })
    public ResultView uploadHeadImg(@RequestHeader HttpHeaders header,
                                    @RequestParam(value = "file", required = false) MultipartFile file, String token) {
        logger.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename().toString());
        ResultView validateResult = redisUtils.getStudentInfo(header, token);
        StudentInfo accoutInfo = (StudentInfo) validateResult.getData();
        if (accoutInfo == null) {
            return validateResult;
        }
        String studentId = accoutInfo.getStudentId();
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
     * @return
     */
    @CrossOrigin
    @RequestMapping(value="/student/loginOut",method= RequestMethod.POST)
    @ApiOperation(value = "loginOut（学生注销登录）",notes = "学生注销登录 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView loginOut(@RequestHeader HttpHeaders header, String studentToken){
        logger.info("开始调用注销接口");
        return redisUtils.delStudentInfo(header,studentToken);
    }
}
