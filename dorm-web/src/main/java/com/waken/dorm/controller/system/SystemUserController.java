package com.waken.dorm.controller.system;

import com.waken.dorm.common.annotation.Limit;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.auth.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.system.RoleService;
import com.waken.dorm.service.system.UserPrivilegeService;
import com.waken.dorm.service.system.UserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description UserController
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@Slf4j
@Api(value = "用户管理", description = "系统管理-用户管理")
@RestController
public class SystemUserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserPrivilegeService userPrivilegeService;

    /**
     * 用户注册
     */
    @Log("保存或修改用户")
    @PostMapping("user/save")
    @ApiOperation(value = "保存或修改用户接口，默认密码\"000000\"", notes = "保存或修改用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresPermissions("users::save")
    public AjaxResponse saveUser(@RequestBody EditUserForm userForm) {
        log.info("开始调用保存或修改用户接口");
        userForm.setUserType(CodeEnum.PLATFORM_USER.getCode());
        return AjaxResponse.success(this.userService.saveUser(userForm));
    }

    @Log("删除用户信息")
    @DeleteMapping("user/delete")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresRoles("superAdmin")
    @RequiresPermissions("users::delete")
    public AjaxResponse deleteUser(@RequestBody DeleteForm deleteFrom) {
        log.info("被删除的用户id" + deleteFrom.getDelIds().toString() + "删除操作人id：" + UserManager.getCurrentUserId());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        userService.deleteUser(deleteFrom);
        return AjaxResponse.success();
    }

    @PostMapping("user/page")
    @ApiOperation(value = "用户信息分页查询", notes = "用户分页查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserView.class)
    })
    @RequiresPermissions("users::view")
    public AjaxResponse findPage(@RequestBody UserForm userForm) {
        log.info("开始调用用户分页查询接口：" + userForm.toString());

        return AjaxResponse.success(this.userService.findPage(userForm));
    }

    @Log("根据用户id查询用户已绑定与未绑定的角色信息")
    @GetMapping("user/roles/{id}")
    @ApiOperation(value = "根据用户id查询用户已绑定与未绑定的角色信息", notes = "根据用户id查询用户已绑定与未绑定的角色信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRolesView.class)
    })
    public AjaxResponse listUserRoles(@PathVariable String id) {
        log.info("开始调用查询用户角色关联信息接口：" + id);
        UserRolesView userRolesView = this.userService.listUserRoles(id);
        return AjaxResponse.success(userRolesView);
    }

    @CrossOrigin
    @GetMapping(value = "user/role/{id}")
    @ApiOperation(value = "通过用户id获取角色信息(属于用户的角色标记为选中状态)", notes = "通过用户id获取角色信息(属于用户的角色标记为选中状态)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRoleView.class)
    })
    public AjaxResponse listRolesByUser(@NotBlank(message = "{required}") @PathVariable String id) {
        log.info("开始调用通过用户id 获取角色信息接口：" + id);
        if (StringUtils.isBlank(id)) {
            return AjaxResponse.error("入参为空！");
        }
        return AjaxResponse.success(roleService.listRolesByUser(id));
    }

    @Log("批量添加用户角色关联")
    @PostMapping("user/batch/add/roles")
    @ApiOperation(value = "给用户绑定角色", notes = "给用户绑定角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresRoles(value = {"superAdmin", "admin"}, logical = Logical.OR)
    @RequiresPermissions("users::roles")
    public AjaxResponse batchAddUserRoleRel(@RequestBody AddUserRoleRelForm addUserRoleRelForm) {
        log.info("开始调用批量添加用户角色关联接口：" + addUserRoleRelForm.toString());
        this.userPrivilegeService.batchAddUserRoleRel(addUserRoleRelForm);
        return AjaxResponse.success();
    }
    @Log("新增单个用户与角色关联")
    @CrossOrigin
    @PostMapping(value = "user/add/role")
    @ApiOperation(value = "addUserRoleRel（新增单个用户与角色关联）", notes = "新增单个用户与角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresPermissions("users::role")
    public AjaxResponse addUserRoleRel(@RequestBody UserRoleRelForm userRoleRelForm) {
        log.info("开始调用新增单个用户与角色关联接口：" + userRoleRelForm);
        if (StringUtils.isBlank(userRoleRelForm.getUserId())) {
            return AjaxResponse.error("用户id为空");
        }
        this.userPrivilegeService.addUserRoleRel(userRoleRelForm);
        return AjaxResponse.success();
    }

    @Log("用户头像上传")
    @PostMapping("user/upload/head-img")
    @ApiOperation(value = "用户头像上传", notes = "用户头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ImgView.class)})
    public AjaxResponse uploadUserImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        try {
            ImgView imgView = this.userService.uploadUserImg(file);
            return AjaxResponse.success(imgView);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传用户头像失败:" + e.getMessage());
            return AjaxResponse.error();
        }
    }

    @Log("导出用户信息")
    @GetMapping("user/export")
    @ApiOperation(value = "export（导出用户信息）", notes = "导出用户信息", produces = "application/octet-stream")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    @RequiresPermissions("users::export")
    public AjaxResponse export(HttpServletResponse response) {
        List<User> userList = this.userService.selectList();
        ExcelKit.$Export(User.class, response).downXlsx(userList, false);
        return AjaxResponse.success();
    }

    @Limit(key = "updatePasswordByCur", period = 60, count = 20, name = "更新当前用户密码", prefix = "limit")
    @PutMapping("user/current/password")
    @ApiOperation(value = "更新当前用户密码", notes = "更新当前用户密码 " +
            "<br/>请求参数JSON示例:{\"oldPassword\":\"000000\",\"newPassword\":\"000000\"}" +
            "<br/>必填参数：oldPassword（原密码） " +
            "<br/>必填参数：newPassword（新密码） " +
            "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    public AjaxResponse updatePasswordByCur(@RequestBody Map<String,String> map) {

        this.userService.updatePassword(UserManager.getCurrentUserId(), map.get("oldPassword"), map.get("newPassword"));

        return AjaxResponse.success();
    }

    @RequiresRoles("superAdmin")
    @Limit(key = "updatePasswordById", period = 60, count = 20, name = "通过用户id更新用户密码", prefix = "limit")
    @PutMapping("user/password")
    @ApiOperation(value = "通过用户id更新用户密码", notes = "通过用户id更新用户密码 " +
            "<br/>请求参数JSON示例:{\"userId\":\"1\",\"oldPassword\":\"000000\",\"newPassword\":\"000000\"}" +
            "<br/>必填参数：userId（用户id） " +
            "<br/>必填参数：oldPassword（原密码） " +
            "<br/>必填参数：newPassword（新密码） " +
            "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    public AjaxResponse updatePasswordById(@RequestBody Map<String,String> map) {

        this.userService.updatePassword(map.get("userId"), map.get("oldPassword"), map.get("newPassword"));

        return AjaxResponse.success();
    }

    @RequiresRoles("superAdmin")
    @PutMapping("user/reset")
    @ApiOperation(value = "通过用户id重置用户密码", notes = "通过用户id重置用户密码 " +
            "<br/>请求参数JSON示例:{\"userId\":\"1\"}" +
            "<br/>必填参数：userId（用户id） " +
            "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    public AjaxResponse resetPasswordById(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");

        if (StringUtils.isBlank(userId)) {
            return AjaxResponse.error("用户id不能为空！");
        }

        this.userService.resetPassword(userId);

        return AjaxResponse.success();
    }

    @RequiresRoles("superAdmin")
    @PutMapping("user/resets")
    @ApiOperation(value = "重置所有用户的密码", notes = "重置所有用户的密码 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = AjaxResponse.class)})
    public AjaxResponse resetAllPassword() {

        this.userService.resetPassword(null);

        return AjaxResponse.success();
    }
}
