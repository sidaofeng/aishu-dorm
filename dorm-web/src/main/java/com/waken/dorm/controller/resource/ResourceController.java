package com.waken.dorm.controller.resource;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.resource.EditResourceForm;
import com.waken.dorm.common.form.resource.ResourceForm;
import com.waken.dorm.common.form.resource.ResourceMenuForm;
import com.waken.dorm.common.form.resource.ResourceTreeForm;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.common.view.resource.ResourceView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.resource.ResourceService;
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
 * @ClassName ResourceController
 * @Description ResourceController
 * @Author zhaoRong
 * @Date 2019/3/26 12:32
 **/
@Api(value = "用户资源相关接口", description = "用户资源相关接口(赵荣)")
@Controller
public class ResourceController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResourceService resourceService;
    @Log("保存或修改资源")
    @CrossOrigin
    @RequestMapping(value= "/resource/saveResource",method= RequestMethod.POST)
    @ApiOperation(value = "saveResource（保存或修改资源）",notes = "保存或修改资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    @ResponseBody
    public ResultView saveResource(@RequestBody EditResourceForm editResourceForm){
        logger.info("开始调用资源保存或修改接口："+editResourceForm.toString());
        ResultView resultView = new ResultView();
        try{
            Resource resource = resourceService.saveResource(editResourceForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(resource);
            resultView.setMsg("保存或修改资源成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用资源保存或修改接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @Log("删除资源")
    @CrossOrigin
    @RequestMapping(value= "/resource/deleteResource",method= RequestMethod.POST)
    @ApiOperation(value = "deleteResource（删除资源）",notes = "删除资源 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteResource(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用资源删除接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            resourceService.deleteResource(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除资源成功");
        }catch (Exception e){
            logger.error("调用资源删除接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/resource/listResourcePages",method= RequestMethod.POST)
    @ApiOperation(value = "listResourcePages（分页查询资源信息）",notes = "分页查询资源信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Resource.class)
    })
    @ResponseBody
    public ResultView listResourcePages(@RequestBody ResourceForm resourceForm){
        logger.info("开始调用分页查询资源信息接口："+resourceForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<Resource> pageInfo = resourceService.listResourcePages(resourceForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询资源信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/resource/listMenuResources",method= RequestMethod.POST)
    @ApiOperation(value = "listMenuResources（查询当前登陆用户所有的菜单资源）",notes = "查询当前登陆用户所有的菜单资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResourceMenuView.class)
    })
    @ResponseBody
    public ResultView listMenuResources(@RequestBody ResourceMenuForm resourceMenuForm){
        logger.info("开始调用查询当前登陆用户所有的菜单资源的接口");
        ResultView resultView = new ResultView();
        try{
            resourceMenuForm.setResourceType(CodeEnum.MENU.getCode());
            List<ResourceMenuView> resourceMenuViews = resourceService.listMenuResources(resourceMenuForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(resourceMenuViews);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询当前登陆用户所有的菜单资源失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/resource/listAllResources",method= RequestMethod.POST)
    @ApiOperation(value = "listAllResources（查询所有的资源(可传角色id查询)）",notes = "查询所有的资源（可传角色id查询）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResourceMenuView.class)
    })
    @ResponseBody
    public ResultView listAllResources(@RequestBody ResourceMenuForm resourceMenuForm){
        logger.info("开始调用查询所有的资源的接口");
        ResultView resultView = new ResultView();
        try{
            List<ResourceMenuView> resourceMenuViews = resourceService.listAllResources(resourceMenuForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(resourceMenuViews);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有的资源失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/resource/listResourceTree",method= RequestMethod.POST)
    @ApiOperation(value = "listResourceTree（查询资源树(可传资源id查询)）",notes = "查询资源树（可传资源id查询）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    @ResponseBody
    public ResultView listResourceTree(@RequestBody ResourceTreeForm resourceTreeForm){
        logger.info("开始调用查询资源树的接口");
        ResultView resultView = new ResultView();
        try{
            List<TreeView> resourceTreeViews = resourceService.listResourceTree(resourceTreeForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(resourceTreeViews);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询资源树失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/resource/queryResourceByPkId",method= RequestMethod.POST)
    @ApiOperation(value = "queryResourceByPkId（通过资源id查询资源)",notes = "通过资源id查询资源")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResourceView.class)
    })
    @ResponseBody
    public ResultView queryResourceByPkId(@RequestBody ResourceTreeForm resourceTreeForm){
        logger.info("开始调用通过资源id查询资源的接口:"+resourceTreeForm);
        ResultView resultView = new ResultView();
        try{
            ResourceView resourceView = resourceService.queryResourceByPkId(resourceTreeForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(resourceView);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("通过资源id查询资源失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
}
