package com.waken.dorm.controller.auth;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.view.role.RoleView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.auth.RoleService;
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

/**
 * @ClassName RoleController
 * @Description RoleController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Slf4j
@Api(value = "用户角色相关接口", description = "用户角色相关接口(AiShu)")
@RestController
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Log("保存或修改角色")
    @CrossOrigin
    @PostMapping(value = "role/save")
    @ApiOperation(value = "保存或修改角色", notes = "保存或修改角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Role.class)
    })
    @RequiresPermissions("roles::save")
    public AjaxResponse saveRole(@RequestBody EditRoleForm editRoleForm) {
        log.info("开始调用角色保存或修改接口：" + editRoleForm.toString());
        return AjaxResponse.success(this.roleService.saveRole(editRoleForm));
    }

    @Log("删除角色")
    @CrossOrigin
    @DeleteMapping(value = "role/delete")
    @ApiOperation(value = "删除角色", notes = "删除角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresPermissions("roles::delete")
    public AjaxResponse deleteRole(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用角色删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        this.roleService.deleteRole(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "role/page")
    @ApiOperation(value = "分页查询角色信息", notes = "分页查询角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = RoleView.class)
    })
    @RequiresPermissions("roles::view")
    public AjaxResponse listRoles(@RequestBody QueryRoleForm queryRoleForm) {
        log.info("开始调用分页查询角色信息接口：" + queryRoleForm.toString());
        return AjaxResponse.success(this.roleService.page(queryRoleForm));
    }

    @Log("批量新增角色资源关联")
    @CrossOrigin
    @PostMapping(value = "role/batch/add/resources")
    @ApiOperation(value = "批量新增角色资源关联", notes = "批量新增角色资源关联")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresRoles(value = {"superAdmin", "admin"}, logical = Logical.OR)
    @RequiresPermissions("roles::resources")
    public AjaxResponse batchAddRoleResourceRel(@RequestBody AddRoleResourceRelForm addForm) {
        log.info("开始调用批量新增角色资源关联接口：" + addForm.toString());
        this.roleService.batchAddRoleResourceRel(addForm);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @GetMapping(value = "roles")
    @ApiOperation(value = "获取所有的角色信息", notes = "获取所有的角色信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getRoleList() {

        return AjaxResponse.success(this.roleService.getRoleList());
    }
}
