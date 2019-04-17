package com.waken.dorm.controller.user;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.QueryUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.utils.ShiroUtils;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName UserController
 * @Description UserController
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@Api(value = "后台用户相关接口", description = "后台用户相关接口(赵荣)")
@Controller
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @Log("保存或修改用户")
    @CrossOrigin
    @RequestMapping(value = {"user/saveUser"},method = RequestMethod.POST)
    @ApiOperation(value = "saveUser（保存或修改用户接口）",notes = "保存或修改用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView saveUser(@RequestBody EditUserForm userForm){
        logger.info("开始调用保存或修改用户接口");
        ResultView resultView = new ResultView();
        try{
            userForm.setUserType(CodeEnum.PLATFORM_USER.getCode());
            userService.saveUser(userForm);
            logger.info("保存或修改用户成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("保存或修改用户成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存或修改用户失败原因是："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("保存或修改用户失败原因："+e.getMessage());
        }
        return resultView;
    }

    /**
     * 用户登录
     */
    @Log("用户登录")
    @CrossOrigin
    @RequestMapping(value = {"user/login"},method = RequestMethod.POST)
    @ApiOperation(value = "login（用户登录接口）",notes = "用户登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView login(@RequestBody QueryUserForm queryUserForm){
        logger.info("开始调用登陆接口："+queryUserForm.getUserName());
        ResultView resultView = new ResultView();
        if (StringUtils.isEmpty(queryUserForm.getUserName()) || StringUtils.isEmpty(queryUserForm.getPassword())) {
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("用户名或密码为空！");
            return resultView;
        }
        logger.info("开始获得subject");
        Subject subject = SecurityUtils.getSubject();
        logger.info("开始获得shiro token");
        UsernamePasswordToken token=new UsernamePasswordToken(queryUserForm.getUserName(),queryUserForm.getPassword());
        try{
            logger.info("开始校验登录信息，提交认证请求");
            subject.login(token);
            logger.info("开始查询用户信息");
            User userInfo = userService.queryUserInfo(queryUserForm);
            logger.info("查询到的用户ID:"+userInfo.getUserId()+"   用户名："+userInfo.getUserName());
            logger.info("开始获得shiro session");
            Session session = SecurityUtils.getSubject().getSession();
            logger.info("sessionID:"+session.getId().toString());
            session.setAttribute("userSession", userInfo);
            session.setAttribute("userSessionId", userInfo.getUserId());
            userInfo.setUserToken(session.getId().toString());
            resultView.setData(userInfo);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("登录成功");
            logger.info("当前登陆用户为："+ShiroUtils.getUserId());
        }catch (AuthenticationException e) {
            token.clear();
            e.printStackTrace();
            logger.error("(shiro)登录失败原因是："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(shiro)用户或密码不正确！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("登录失败原因是："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("用户或密码不正确");
        }
        return resultView;
    }

    /**
     * 注销登录
     * @return
     */
    @CrossOrigin
    @RequestMapping(value="/user/logout",method= RequestMethod.POST)
    @ApiOperation(value = "logout（用户注销）",notes = "用户注销 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView logout(){
        logger.info("开始调用注销接口");
        ResultView resultView = new ResultView();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("注销成功！");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("注销失败原因是:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("注销失败");
        }
        return resultView;
    }
    @Log("删除用户信息")
    @CrossOrigin
    @RequestMapping(value="/user/deleteUser",method= RequestMethod.POST)
    @ApiOperation(value = "deleteUser（删除用户信息）",notes = "删除用户信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteUser(@RequestBody DeleteForm deleteFrom){
        logger.info("被删除的用户id"+deleteFrom.getDelIds().toString() + "删除操作人id：" + ShiroUtils.getUserId());
        ResultView resultView = new ResultView();
        try{
            userService.deleteUser(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除用户成功");
            logger.info("删除用户成功");
        }catch (Exception e){
            logger.error("删除用户失败,原因:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());

        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value="/user/listUsers",method= RequestMethod.POST)
    @ApiOperation(value = "listUsers（用户信息分页查询）",notes = "用户分页查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserView.class)
    })
    @ResponseBody
    public ResultView listUsers(@RequestBody UserForm userForm){
        logger.info("开始调用用户分页查询接口："+userForm.toString());
        ResultView resultView = new ResultView();
        PageInfo<UserView> pageInfo = new PageInfo();
        try{
            pageInfo = userService.listUsers(userForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用用户分页查询接口失败，原因:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("调用用户分页查询接口失败，原因:"+e.getMessage());
        }
        return resultView;
    }
    @Log("批量添加用户角色关联")
    @CrossOrigin
    @RequestMapping(value="/user/batchAddUserRoleRel",method= RequestMethod.POST)
    @ApiOperation(value = "batchAddUserRoleRel（批量添加用户角色关联）",notes = "批量添加用户角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView batchAddUserRoleRel(@RequestBody AddUserRoleRelForm addUserRoleRelForm){
        logger.info("开始调用批量添加用户角色关联接口："+addUserRoleRelForm.toString());
        ResultView resultView = new ResultView();
        try{
            userService.batchAddUserRoleRel(addUserRoleRelForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("添加用户角色关联成功！");
        }catch (Exception e){
            logger.error("调用批量添加用户角色关联接口失败，原因:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value="/user/listUserRoles",method= RequestMethod.POST)
    @ApiOperation(value = "listUserRoles（查询用户角色关联信息）",notes = "查询用户角色关联信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRolesView.class)
    })
    @ResponseBody
    public ResultView listUserRoles(@RequestBody ListAddedRoleForm listAddedRoleForm){
        logger.info("开始调用查询用户角色关联信息接口："+listAddedRoleForm.toString());
        ResultView resultView = new ResultView();
        try{
            UserRolesView userRolesView = userService.listUserRoles(listAddedRoleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(userRolesView);
            resultView.setMsg("查询用户角色关联信息成功");
        }catch (Exception e){
            logger.error("调用查询用户角色关联信息接口失败:",e);
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @Log("用户头像上传")
    @CrossOrigin
    @RequestMapping(value = "/user/uploadUserImg", method = RequestMethod.POST)
    @ApiOperation(value = "uploadUserImg（用户头像上传）", notes = "用户头像上传")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = ResultView.class) })
    @ResponseBody
    public ResultView uploadUserImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename().toString());
        ResultView resultView = new ResultView();
        try {
            ImgView imgView = userService.uploadUserImg(file);
            resultView.setMsg("用户头像上传成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(imgView);
            logger.info("用户头像成功！");
        } catch (Exception e) {
            resultView.setMsg("用户头像上传失败:" + e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
