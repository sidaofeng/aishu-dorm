package com.waken.dorm.controller.user;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.AddUserResourcesForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.role.RoleService;
import com.waken.dorm.service.user.UserPrivilegeService;
import com.waken.dorm.service.user.UserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@Slf4j
@Api(value = "后台用户相关接口", description = "后台用户相关接口(AiShu)")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserPrivilegeService userPrivilegeService;

    /**
     * 用户注册
     */
    @Log("保存或修改用户")
    @PostMapping("user/save")
    @ApiOperation(value = "保存或修改用户接口", notes = "保存或修改用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresPermissions("users::save")
    public AjaxResponse saveUser(@RequestBody EditUserForm userForm) {
        log.info("开始调用保存或修改用户接口");
        userForm.setUserType(CodeEnum.PLATFORM_USER.getCode());
        return AjaxResponse.success(userService.saveUser(userForm));
    }

    @Log("删除用户信息")
    @DeleteMapping("user/delete")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
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
    public AjaxResponse listUsers(@RequestBody UserForm userForm) {
        log.info("开始调用用户分页查询接口：" + userForm.toString());
        PageInfo<UserView> pageInfo = userService.listUsers(userForm);
        return AjaxResponse.success(pageInfo);
    }

    @Log("根据用户id查询用户已绑定与未绑定的角色信息")
    @GetMapping("user/roles/{id}")
    @ApiOperation(value = "根据用户id查询用户已绑定与未绑定的角色信息", notes = "根据用户id查询用户已绑定与未绑定的角色信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRolesView.class)
    })
    public AjaxResponse listUserRoles(@PathVariable String id) {
        log.info("开始调用查询用户角色关联信息接口：" + id);
        UserRolesView userRolesView = userService.listUserRoles(id);
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
    @RequiresPermissions("users::roles")
    public AjaxResponse batchAddUserRoleRel(@RequestBody AddUserRoleRelForm addUserRoleRelForm) {
        log.info("开始调用批量添加用户角色关联接口：" + addUserRoleRelForm.toString());
        userPrivilegeService.batchAddUserRoleRel(addUserRoleRelForm);
        return AjaxResponse.success();
    }

    @Log("批量添加用户资源关联")
    @PostMapping("user/batch/add/resources")
    @ApiOperation(value = "给用户绑定资源", notes = "给用户绑定资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresPermissions("users::resources")
    public AjaxResponse batchAddUserRoleRel(@RequestBody AddUserResourcesForm addForm) {
        log.info("开始调用批量添加用户角色关联接口：" + addForm.toString());
        userPrivilegeService.batchAddUserResourceRel(addForm);
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
        userPrivilegeService.addUserRoleRel(userRoleRelForm);
        return AjaxResponse.success();
    }

    @Log("用户头像上传")
    @PostMapping("user/upload/head-img")
    @ApiOperation(value = "用户头像上传", notes = "用户头像上传")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = ImgView.class)})
    public AjaxResponse uploadUserImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用图片文件上传接口，上传图片文件为：" + file.getOriginalFilename());
        try {
            ImgView imgView = userService.uploadUserImg(file);
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
        List<User> userList = userService.selectList();
        ExcelKit.$Export(User.class, response).downXlsx(userList, false);
        return AjaxResponse.success();
    }
}
