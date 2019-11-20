package com.waken.dorm.controller.auth;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditButtonsForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.auth.ResourceService;
import com.waken.dorm.service.auth.UserPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ResourceController
 * @Description ResourceController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Slf4j
@Api(value = "用户资源相关接口", description = "用户资源相关接口(AiShu)")
@RestController
public class ResourceController extends BaseController {
    @Autowired
    ResourceService resourceService;
    @Autowired
    UserPrivilegeService userPrivilegeService;

    @Log("保存或修改资源")
    @CrossOrigin
    @PostMapping(value = "resources/save")
    @ApiOperation(value = "保存或修改资源", notes = "保存或修改资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    @RequiresPermissions("resources::save")
    public AjaxResponse saveResource(@RequestBody EditResourceForm editResourceForm) {
        log.info("开始调用资源保存或修改接口：" + editResourceForm.toString());
        return AjaxResponse.success(resourceService.saveResource(editResourceForm));
    }

    @Log("删除资源")
    @CrossOrigin
    @DeleteMapping(value = "resources/delete")
    @ApiOperation(value = "删除资源", notes = "删除资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresRoles("superAdmin")
    public AjaxResponse deleteResource(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用资源删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        resourceService.deleteResource(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @GetMapping("resources/menu")
    @ApiOperation(value = "查询当前登陆用户拥有的菜单资源", notes = "查询当前登陆用户所有的菜单资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserMenuView.class)
    })
    public AjaxResponse getMenuByUser() {
        log.info("开始调用查询当前登陆用户所有的菜单资源的接口");
        return AjaxResponse.success(userPrivilegeService.getUserMenu(UserManager.getCurrentUserId()));
    }

    @CrossOrigin
    @GetMapping(value = "resources/tree")
    @ApiOperation(value = "查询资源树", notes = "查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    @RequiresPermissions("resources::view")
    public AjaxResponse getResourcesTree() {
        log.info("开始调用查询资源树的接口");
        return AjaxResponse.success(resourceService.getResourcesTree(null, null));
    }

    @CrossOrigin
    @GetMapping(value = "resources/tree/user/{id}")
    @ApiOperation(value = "根据用户id查询资源树", notes = "根据用户id查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public AjaxResponse getTreeByUser(@PathVariable("id") String id) {
        log.info("开始调用通过用户id查询资源树的接口:" + id);
        if (StringUtils.isBlank(id)) {
            return AjaxResponse.error("入参为空！");
        }
        return AjaxResponse.success(resourceService.getResourcesTree(id, null));
    }

    @CrossOrigin
    @GetMapping(value = "resources/tree/role/{id}")
    @ApiOperation(value = "根据角色id查询资源树", notes = "根据角色id查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public AjaxResponse getTreeByRole(@PathVariable("id") String id) {
        log.info("开始调用通过角色id查询资源树的接口:" + id);
        if (StringUtils.isBlank(id)) {
            return AjaxResponse.error("入参为空！");
        }
        return AjaxResponse.success(resourceService.getResourcesTree(null, id));
    }

    @CrossOrigin
    @GetMapping(value = "resources/{id}")
    @ApiOperation(value = "通过资源id查询资源", notes = "通过资源id查询资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    public AjaxResponse selectById(@PathVariable("id") String id) {
        log.info("开始调用通过资源id查询资源的接口:" + id);
        if (StringUtils.isBlank(id)) {
            return AjaxResponse.error("参数为空！");
        }
        Resource resource = resourceService.selectById(id);
        if (null == resource) {
            return AjaxResponse.error("参数错误！");
        }
        return AjaxResponse.success(resource);
    }

    @CrossOrigin
    @GetMapping(value = "buttons/{id}")
    @ApiOperation(value = "通过资源id查询对应的按钮权限", notes = "通过资源id查询对应的按钮权限")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    public AjaxResponse selectButtonByResourceId(@PathVariable("id") String id){

        if (StringUtils.isEmpty(id)){
            return AjaxResponse.error("参数为空！");
        }
        return AjaxResponse.success(resourceService.selectButtonByResourceId(id));
    }

    @Log("批量保存按钮资源")
    @CrossOrigin
    @PostMapping(value = "buttons/save")
    @ApiOperation(value = "批量保存按钮资源", notes = "批量保存按钮资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @RequiresRoles("superAdmin")
//    @RequiresPermissions("buttons::save")
    public AjaxResponse batchSaveButton(@RequestBody EditButtonsForm editButtonsForm) {

        return AjaxResponse.success(resourceService.batchSaveButton(editButtonsForm));
    }
}
