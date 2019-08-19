package com.waken.dorm.controller.role;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RoleController
 * @Description RoleController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Api(value = "用户角色相关接口", description = "用户角色相关接口(AiShu)")
@RestController
public class RoleController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
        logger.info("开始调用角色保存或修改接口：" + editRoleForm.toString());
        try {
            return ResultUtil.success(roleService.saveRole(editRoleForm));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用角色保存或修改接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除角色")
    @CrossOrigin
    @DeleteMapping(value = "role/delete")
    @ApiOperation(value = "删除角色", notes = "删除角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteRole(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用角色删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() && deleteFrom.getDelIds().isEmpty() && null == deleteFrom.getDelStatus()) {
            return ResultUtil.errorByMsg("入参为空！");
        }
        roleService.deleteRole(deleteFrom);
        return ResultUtil.success();
    }

    @CrossOrigin
    @GetMapping(value = "roles/user/{id}")
    @ApiOperation(value = "通过用户id 获取角色信息", notes = "通过用户id 获取角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRoleView.class)
    })
    public ResultView listUserRoleByUserId(@NotBlank(message = "{required}") @PathVariable String id) {
        logger.info("开始调用通过用户id 获取角色信息接口：" + id);
        if (StringUtils.isBlank(id)){
            return ResultUtil.errorByMsg("入参为空！");
        }
        return ResultUtil.success(roleService.listUserRoleByUserId(id));
    }

    @Log("新增单个用户与角色关联")
    @CrossOrigin
    @PostMapping(value = "role/add-user-role")
    @ApiOperation(value = "addUserRoleRel（新增单个用户与角色关联）", notes = "新增单个用户与角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView addUserRoleRel(@RequestBody UserRoleRelForm userRoleRelForm) {
        logger.info("开始调用新增单个用户与角色关联接口：" + userRoleRelForm);
        if (StringUtils.isEmpty(userRoleRelForm.getUserId())) {
            return ResultUtil.errorByMsg("用户id为空");
        }
        roleService.addUserRoleRel(userRoleRelForm);
        return ResultUtil.success();
    }

    @CrossOrigin
    @PostMapping(value = "role/page")
    @ApiOperation(value = "分页查询角色信息", notes = "分页查询角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Role.class)
    })
    public ResultView listRoles(@RequestBody QueryRoleForm queryRoleForm) {
        logger.info("开始调用分页查询角色信息接口：" + queryRoleForm.toString());
        return ResultUtil.success(roleService.listRoles(queryRoleForm));
    }

    @Log("批量新增角色资源关联")
    @CrossOrigin
    @PostMapping(value = "role/add-role-resources")
    @ApiOperation(value = "批量新增角色资源关联", notes = "批量新增角色资源关联")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchAddRoleResourceRel(@RequestBody AddRoleResourceRelForm addRoleResourceRelForm) {
        logger.info("开始调用批量新增角色资源关联接口：" + addRoleResourceRelForm.toString());
        try {
            roleService.batchAddRoleResourceRel(addRoleResourceRelForm);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用批量新增角色资源关联接口失败，原因:" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
