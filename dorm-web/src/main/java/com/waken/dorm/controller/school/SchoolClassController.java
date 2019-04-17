package com.waken.dorm.controller.school;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.school.SchoolClass;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassTreeForm;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.school.SchoolClassView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.school.SchoolClassService;
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
 * @ClassName SchoolClassController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/4 12:53
 **/
@Api(value = "学校类别相关接口", description = "学校类别相关接口(赵荣)")
@Controller
public class SchoolClassController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SchoolClassService schoolClassService;
    @Log("(保存/修改)学校类别信息")
    @CrossOrigin
    @RequestMapping(value= "/schoolClass/saveSchoolClass",method= RequestMethod.POST)
    @ApiOperation(value = "saveSchoolClass（(保存/修改)学校类别信息）",notes = "(保存/修改)学校类别信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolClass.class)
    })
    @ResponseBody
    public ResultView saveSchoolClass(@RequestBody EditSchoolClassForm editSchoolClassForm){
        logger.info("开始调用(保存/修改)学校类别信息接口接口："+editSchoolClassForm.toString());
        ResultView resultView = new ResultView();
        try{
            SchoolClass SchoolClass = schoolClassService.saveSchoolClass(editSchoolClassForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(SchoolClass);
            resultView.setMsg("(保存/修改)学校类别信息成功");
            logger.info("(保存/修改)学校类别信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)学校类别信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)学校类别失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除学校类别信息")
    @CrossOrigin
    @RequestMapping(value= "/schoolClass/deleteSchoolClass",method= RequestMethod.POST)
    @ApiOperation(value = "deleteSchoolClass（删除学校类别信息）",notes = "删除学校类别信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteSchoolClass(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除学校类别接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            schoolClassService.deleteSchoolClass(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除学校类别成功");
            logger.info("删除学校类别成功");
        }catch (Exception e){
            logger.error("调用删除学校类别接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/schoolClass/listSchoolClasses",method= RequestMethod.POST)
    @ApiOperation(value = "listSchoolClasses（查询学校类别集合）",notes = "查询学校类别集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolClassView.class)
    })
    @ResponseBody
    public ResultView listSchoolClasses(@RequestBody SchoolClassForm schoolClassForm){
        logger.info("开始调用查询学校类别集合接口："+schoolClassForm.toString());
        ResultView resultView = new ResultView();
        try{
            List<SchoolClassView> schoolClassViews = schoolClassService.listSchoolClasses(schoolClassForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(schoolClassViews);
            logger.info("查询学校类别集合成功");
        }catch (Exception e){
            logger.error("调用查询学校类别集合失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/schoolClass/listSchoolClassTree",method= RequestMethod.POST)
    @ApiOperation(value = "listSchoolClassTree（查询学校类别树(可传学校类别id查询)）",notes = "查询学校类别树（可传学校类别id查询）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TreeView.class)
    })
    @ResponseBody
    public ResultView listSchoolClassTree(@RequestBody SchoolClassTreeForm schoolClassTreeForm){
        logger.info("开始调用查询学校类别树的接口");
        ResultView resultView = new ResultView();
        try{
            List<TreeView> schoolClassTreeViewList = schoolClassService.listSchoolClassTree(schoolClassTreeForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(schoolClassTreeViewList);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询学校类别树失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/schoolClass/selectByPkId",method= RequestMethod.POST)
    @ApiOperation(value = "selectByPkId（通过学校类别id查询类别信息",notes = "查询学校类别树（可传学校类别id查询）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolClass.class)
    })
    @ResponseBody
    public ResultView selectByPkId(@RequestBody SchoolClassTreeForm schoolClassTreeForm){
        logger.info("开始调用通过学校类别id查询类别信息接口");
        ResultView resultView = new ResultView();
        try{
            SchoolClass schoolClass = schoolClassService.selectByPkId(schoolClassTreeForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(schoolClass);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("通过学校类别id查询类别信息失败，原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
}
