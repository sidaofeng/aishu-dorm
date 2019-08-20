package com.waken.dorm.controller.resource;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.resource.ResourceService;
import com.waken.dorm.service.user.UserPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResultView saveResource(@RequestBody EditResourceForm editResourceForm) {
        log.info("开始调用资源保存或修改接口：" + editResourceForm.toString());
        return ResultUtil.success(resourceService.saveResource(editResourceForm));
    }

    @Log("删除资源")
    @CrossOrigin
    @DeleteMapping(value = "resources/delete")
    @ApiOperation(value = "删除资源", notes = "删除资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteResource(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用资源删除接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return ResultUtil.errorByMsg("入参为空！");
        }
        resourceService.deleteResource(deleteFrom);
        return ResultUtil.success();
    }

    @CrossOrigin
    @GetMapping(value = "resources/menu")
    @ApiOperation(value = "查询当前登陆用户拥有的菜单资源", notes = "查询当前登陆用户所有的菜单资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserMenuView.class)
    })
    public ResultView getMenuByUser() {
        log.info("开始调用查询当前登陆用户所有的菜单资源的接口");
        return ResultUtil.success(userPrivilegeService.getUserMenu(UserManager.getCurrentUserId()));
    }

    @CrossOrigin
    @GetMapping(value = "resources/tree")
    @ApiOperation(value = "查询资源树", notes = "查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public ResultView getResourcesTree() {
        log.info("开始调用查询资源树的接口");
        return ResultUtil.success(resourceService.getResourcesTree(null,null,null));
    }

    @CrossOrigin
    @GetMapping(value = "resources/tree/user/{id}")
    @ApiOperation(value = "根据用户id查询资源树", notes = "根据用户id查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public ResultView getTreeByUser(@PathVariable String id) {
        log.info("开始调用通过用户id查询资源树的接口:" + id);
        if (StringUtils.isBlank(id)){
            return ResultUtil.errorByMsg("入参为空！");
        }
        return ResultUtil.success(resourceService.getResourcesTree(CodeEnum.ENABLE.getCode(),id,null));
    }
    @CrossOrigin
    @GetMapping(value = "resources/tree/role/{id}")
    @ApiOperation(value = "根据角色id查询资源树", notes = "根据角色id查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public ResultView getTreeByRole(@PathVariable String id) {
        log.info("开始调用通过角色id查询资源树的接口:" + id);
        if (StringUtils.isBlank(id)){
            return ResultUtil.errorByMsg("入参为空！");
        }
        return ResultUtil.success(resourceService.getResourcesTree(CodeEnum.ENABLE.getCode(),null,id));
    }

    @CrossOrigin
    @GetMapping(value = "resources/{id}")
    @ApiOperation(value = "通过资源id查询资源", notes = "通过资源id查询资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    public ResultView selectById(@PathVariable String id) {
        log.info("开始调用通过资源id查询资源的接口:" + id);
        if (StringUtils.isBlank(id)){
            return ResultUtil.errorByMsg("参数为空！");
        }
        Resource resource = resourceService.selectById(id);
        if (null == resource){
            return ResultUtil.errorByMsg("参数错误！");
        }
        return ResultUtil.success(resource);
    }
}
