package com.waken.dorm.controller.role;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
    public ResultView saveRole(@RequestBody EditRoleForm editRoleForm) {
        log.info("开始调用角色保存或修改接口：" + editRoleForm.toString());
        return ResultUtil.success(roleService.saveRole(editRoleForm));
    }

    @Log("删除角色")
    @CrossOrigin
    @DeleteMapping(value = "role/delete")
    @ApiOperation(value = "删除角色", notes = "删除角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteRole(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用角色删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return ResultUtil.errorByMsg("入参为空！");
        }
        roleService.deleteRole(deleteFrom);
        return ResultUtil.success();
    }

    @CrossOrigin
    @PostMapping(value = "role/page")
    @ApiOperation(value = "分页查询角色信息", notes = "分页查询角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Role.class)
    })
    public ResultView listRoles(@RequestBody QueryRoleForm queryRoleForm) {
        log.info("开始调用分页查询角色信息接口：" + queryRoleForm.toString());
        return ResultUtil.success(roleService.listRoles(queryRoleForm));
    }

    @Log("批量新增角色资源关联")
    @CrossOrigin
    @PostMapping(value = "role/batch/add/resources")
    @ApiOperation(value = "批量新增角色资源关联", notes = "批量新增角色资源关联")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddRoleResourceRel(@RequestBody AddRoleResourceRelForm addForm) {
        log.info("开始调用批量新增角色资源关联接口：" + addForm.toString());
        roleService.batchAddRoleResourceRel(addForm);
        return ResultUtil.success();
    }
}
