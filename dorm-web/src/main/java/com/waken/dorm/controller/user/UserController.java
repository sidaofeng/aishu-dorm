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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName UserController
 * @Description UserController
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@Api(value = "后台用户相关接口", description = "后台用户相关接口(AiShu)")
@RestController
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
        logger.info("开始调用保存或修改用户接口");
        try {
            userForm.setUserType(CodeEnum.PLATFORM_USER.getCode());
            userService.saveUser(userForm);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("保存或修改用户失败原因是：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除用户信息")
    @DeleteMapping("user/delete")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteUser(@RequestBody DeleteForm deleteFrom) {
        logger.info("被删除的用户id" + deleteFrom.getDelIds().toString() + "删除操作人id：" + UserManager.getCurrentUserId());
        try {
            userService.deleteUser(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除用户失败,原因:" + e.getMessage());
            return ResultUtil.error();

        }
    }

    @GetMapping("user/page")
    @ApiOperation(value = "用户信息分页查询", notes = "用户分页查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserView.class)
    })
    public ResultView listUsers(UserForm userForm) {
        logger.info("开始调用用户分页查询接口：" + userForm.toString());
        try {
            PageInfo<UserView> pageInfo = userService.listUsers(userForm);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用用户分页查询接口失败，原因:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("批量添加用户角色关联")
    @PostMapping("user/batch-add-user-role")
    @ApiOperation(value = "批量添加用户角色关联", notes = "批量添加用户角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddUserRoleRel(@RequestBody AddUserRoleRelForm addUserRoleRelForm) {
        logger.info("开始调用批量添加用户角色关联接口：" + addUserRoleRelForm.toString());
        try {
            userService.batchAddUserRoleRel(addUserRoleRelForm);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用批量添加用户角色关联接口失败，原因:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @GetMapping("user/user-roles/{id}")
    @ApiOperation(value = "listUserRoles（根据用户id查询用户角色关联信息）", notes = "查询用户角色关联信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRolesView.class)
    })
    public ResultView listUserRoles(@NotBlank(message = "{required}") @PathVariable String id) {
        logger.info("开始调用查询用户角色关联信息接口：" + id);
        try {
            ListAddedRoleForm form = new ListAddedRoleForm();
            form.setUserId(id);
            UserRolesView userRolesView = userService.listUserRoles(form);
            return ResultUtil.success(userRolesView);
        } catch (Exception e) {
            logger.error("调用查询用户角色关联信息接口失败:", e);
            return ResultUtil.error();
        }
    }

    @Log("用户头像上传")
    @PostMapping("user/upload-head-img")
    @ApiOperation(value = "用户头像上传", notes = "用户头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ImgView.class)})
    public ResultView uploadUserImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        try {
            ImgView imgView = userService.uploadUserImg(file);
            return ResultUtil.success(imgView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传用户头像失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("导出用户信息")
    @GetMapping("user/export")
    @ApiOperation(value = "export（导出用户信息）", notes = "导出用户信息",produces="application/octet-stream")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ResultView.class)})
    public ResultView export(HttpServletResponse response) {
        try {
            List<User> userList = userService.selectList();
            ExcelKit.$Export(User.class, response).downXlsx(userList, false);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.info("导出用户信息失败！");
            return ResultUtil.error();
        }
    }
}
