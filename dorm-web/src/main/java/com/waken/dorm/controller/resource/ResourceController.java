package com.waken.dorm.controller.resource;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.utils.ResultUtil;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ResourceController
 * @Description ResourceController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Api(value = "用户资源相关接口", description = "用户资源相关接口(AiShu)")
@RestController
public class ResourceController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ResourceService resourceService;
    @Autowired
    UserPrivilegeService userPrivilegeService;

    @Log("保存或修改资源")
    @CrossOrigin
    @PostMapping(value = "resource/save")
    @ApiOperation(value = "保存或修改资源", notes = "保存或修改资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    public ResultView saveResource(@RequestBody EditResourceForm editResourceForm) {
        logger.info("开始调用资源保存或修改接口：" + editResourceForm.toString());
        try {
            return ResultUtil.success(resourceService.saveResource(editResourceForm));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用资源保存或修改接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("删除资源")
    @CrossOrigin
    @DeleteMapping(value = "resource/delete")
    @ApiOperation(value = "删除资源", notes = "删除资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteResource(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用资源删除接口：" + deleteFrom.toString());
        try {
            resourceService.deleteResource(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用资源删除接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "resource/menu")
    @ApiOperation(value = "查询当前登陆用户拥有的菜单资源", notes = "查询当前登陆用户所有的菜单资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserMenuView.class)
    })
    public ResultView getMenuByUser() {
        logger.info("开始调用查询当前登陆用户所有的菜单资源的接口");
        try {
            return ResultUtil.success(userPrivilegeService.getUserMenu(UserManager.getCurrentUserId()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询当前登陆用户所有的菜单资源失败，原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "resource/tree")
    @ApiOperation(value = "查询资源树", notes = "查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public ResultView getAllResourceTree() {
        logger.info("开始调用查询资源树的接口");
        try {
            return ResultUtil.success(resourceService.getAllResourceTree(null,null,null));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询资源树失败，原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "resource/user-role-tree")
    @ApiOperation(value = "根据用户id或角色id查询资源树", notes = "根据用户id或角色id查询资源树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    public ResultView getUserOrRoleResourceTree(@RequestParam(required = false) String userId,
                                                @RequestParam(required = false) String roleId) {
        try {
            return ResultUtil.success(resourceService.getAllResourceTree(CodeEnum.ENABLE.getCode(),userId,roleId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询资源树失败，原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "resource/{id}")
    @ApiOperation(value = "通过资源id查询资源", notes = "通过资源id查询资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    public ResultView selectById(@NotBlank(message = "{required}") @PathVariable String id) {
        logger.info("开始调用通过资源id查询资源的接口:" + id);
        try {
            return ResultUtil.success(resourceService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("通过资源id查询资源失败，原因：" + e.getMessage());
            return ResultUtil.error();
        }
    }
}
