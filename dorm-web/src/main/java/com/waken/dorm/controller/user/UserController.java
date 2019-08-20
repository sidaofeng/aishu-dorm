package com.waken.dorm.controller.user;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.user.UserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName UserController
 * @Description UserController
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@Slf4j
@Api(value = "后台用户相关接口", description = "后台用户相关接口(AiShu)")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     */
    @Log("保存或修改用户")
    @PostMapping("user/save")
    @ApiOperation(value = "保存或修改用户接口", notes = "保存或修改用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView saveUser(@RequestBody EditUserForm userForm) {
        log.info("开始调用保存或修改用户接口");
        userForm.setUserType(CodeEnum.PLATFORM_USER.getCode());
        return ResultUtil.success(userService.saveUser(userForm));
    }

    @Log("删除用户信息")
    @DeleteMapping("user/delete")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteUser(@RequestBody DeleteForm deleteFrom) {
        log.info("被删除的用户id" + deleteFrom.getDelIds().toString() + "删除操作人id：" + UserManager.getCurrentUserId());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return ResultUtil.errorByMsg("入参为空！");
        }
        userService.deleteUser(deleteFrom);
        return ResultUtil.success();
    }

    @PostMapping("user/page")
    @ApiOperation(value = "用户信息分页查询", notes = "用户分页查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserView.class)
    })
    public ResultView listUsers(@RequestBody UserForm userForm) {
        log.info("开始调用用户分页查询接口：" + userForm.toString());
        PageInfo<UserView> pageInfo = userService.listUsers(userForm);
        return ResultUtil.success(pageInfo);
    }

    @Log("批量添加用户角色关联")
    @PostMapping("user/batch-add-user-role")
    @ApiOperation(value = "批量添加用户角色关联", notes = "批量添加用户角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddUserRoleRel(@RequestBody AddUserRoleRelForm addUserRoleRelForm) {
        log.info("开始调用批量添加用户角色关联接口：" + addUserRoleRelForm.toString());
        userService.batchAddUserRoleRel(addUserRoleRelForm);
        return ResultUtil.success();
    }

    @GetMapping("user/roles/{id}")
    @ApiOperation(value = "listUserRoles（根据用户id查询用户角色关联信息）", notes = "查询用户角色关联信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRolesView.class)
    })
    public ResultView listUserRoles(@PathVariable String id) {
        log.info("开始调用查询用户角色关联信息接口：" + id);
        ListAddedRoleForm form = new ListAddedRoleForm();
        form.setUserId(id);
        UserRolesView userRolesView = userService.listUserRoles(form);
        return ResultUtil.success(userRolesView);
    }

    @Log("用户头像上传")
    @PostMapping("user/upload-head-img")
    @ApiOperation(value = "用户头像上传", notes = "用户头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ImgView.class)})
    public ResultView uploadUserImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        try {
            ImgView imgView = userService.uploadUserImg(file);
            return ResultUtil.success(imgView);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传用户头像失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("导出用户信息")
    @GetMapping("user/export")
    @ApiOperation(value = "export（导出用户信息）", notes = "导出用户信息",produces="application/octet-stream")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ResultView.class)})
    public ResultView export(HttpServletResponse response) {
        List<User> userList = userService.selectList();
        ExcelKit.$Export(User.class, response).downXlsx(userList, false);
        return ResultUtil.success();
    }
}
