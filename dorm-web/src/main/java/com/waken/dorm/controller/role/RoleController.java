package com.waken.dorm.controller.role;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName RoleController
 * @Description RoleController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Api(value = "用户角色相关接口", description = "用户角色相关接口(赵荣)")
@Controller
public class RoleController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleService roleService;
    @Log("保存或修改角色")
    @CrossOrigin
    @RequestMapping(value= "/role/saveRole",method= RequestMethod.POST)
    @ApiOperation(value = "saveRole（保存或修改角色）",notes = "保存或修改角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Role.class)
    })
    @ResponseBody
    public ResultView saveRole(@RequestBody EditRoleForm editRoleForm){
        logger.info("开始调用角色保存或修改接口："+editRoleForm.toString());
        ResultView resultView = new ResultView();
        try{
            Role role = roleService.saveRole(editRoleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(role);
            resultView.setMsg("保存或修改角色成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用角色保存或修改接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @Log("删除角色")
    @CrossOrigin
    @RequestMapping(value= "/role/deleteRole",method= RequestMethod.POST)
    @ApiOperation(value = "deleteRole（删除角色）",notes = "删除角色 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteRole(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用角色删除接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            roleService.deleteRole(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除角色成功");
        }catch (Exception e){
            logger.error("调用角色删除接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/role/listUserRoleByUserId",method= RequestMethod.POST)
    @ApiOperation(value = "listUserRoleByUserId（通过用户id 获取角色信息）",notes = "通过用户id 获取角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserRoleView.class)
    })
    @ResponseBody
    public ResultView listUserRoleByUserId(@RequestBody EditUserForm editUserForm){
        logger.info("开始调用通过用户id 获取角色信息接口："+editUserForm.getUserId());
        ResultView resultView = new ResultView();
        try{
            List<UserRoleView> userRoleViews = roleService.listUserRoleByUserId(editUserForm.getUserId());
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(userRoleViews);
        }catch (Exception e){
            logger.error("调用通过用户id 获取角色信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @Log("新增单个用户与角色关联")
    @CrossOrigin
    @RequestMapping(value= "/role/addUserRoleRel",method= RequestMethod.POST)
    @ApiOperation(value = "addUserRoleRel（新增单个用户与角色关联）",notes = "新增单个用户与角色关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success",response = ResultView.class)
    })
    @ResponseBody
    public ResultView addUserRoleRel(@RequestBody UserRoleRelForm userRoleRelForm){
        logger.info("开始调用新增单个用户与角色关联接口："+userRoleRelForm);
        ResultView resultView = new ResultView();
        try{
            roleService.addUserRoleRel(userRoleRelForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
        }catch (Exception e){
            logger.error("调用新增单个用户与角色关联失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/role/listRoles",method= RequestMethod.POST)
    @ApiOperation(value = "listRoles（分页查询角色信息）",notes = "分页查询角色信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Role.class)
    })
    @ResponseBody
    public ResultView listRoles(@RequestBody QueryRoleForm queryRoleForm){
        logger.info("开始调用分页查询角色信息接口："+queryRoleForm.toString());
        ResultView resultView = new ResultView();
        PageInfo<Role> pageInfo = new PageInfo<Role>();
        try{
            pageInfo = roleService.listRoles(queryRoleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询角色信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @Log("批量新增角色资源关联")
    @CrossOrigin
    @RequestMapping(value= "/role/batchAddRoleResourceRel",method= RequestMethod.POST)
    @ApiOperation(value = "batchAddRoleResourceRel（批量新增角色资源关联）",notes = "批量新增角色资源关联")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView batchAddRoleResourceRel(@RequestBody AddRoleResourceRelForm addRoleResourceRelForm){
        logger.info("开始调用批量新增角色资源关联接口："+addRoleResourceRelForm.toString());
        ResultView resultView = new ResultView();
        try{
            roleService.batchAddRoleResourceRel(addRoleResourceRelForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("批量新增角色资源关联成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用批量新增角色资源关联接口失败，原因:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
}
