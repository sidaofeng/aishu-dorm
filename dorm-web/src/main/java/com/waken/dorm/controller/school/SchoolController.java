package com.waken.dorm.controller.school;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.school.School;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolForm;
import com.waken.dorm.common.form.school.SchoolForm;
import com.waken.dorm.common.view.school.SchoolView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.school.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SchoolController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 20:39
 **/
@Api(value = "学校相关接口", description = "学校相关接口(赵荣)")
@Controller
public class SchoolController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchoolService schoolService;
    @Log("(保存/修改)学校信息")
    @CrossOrigin
    @RequestMapping(value= "/school/saveSchool",method= RequestMethod.POST)
    @ApiOperation(value = "saveSchool（(保存/修改)学校信息）",notes = "(保存/修改)学校信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = School.class)
    })
    @ResponseBody
    public ResultView saveSchool(@RequestBody EditSchoolForm editSchoolForm){
        logger.info("开始调用(保存/修改)学校信息接口接口："+editSchoolForm.toString());
        ResultView resultView = new ResultView();
        try{
            School School = schoolService.saveSchool(editSchoolForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(School);
            resultView.setMsg("(保存/修改)学校信息成功");
            logger.info("(保存/修改)学校信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)学校信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)学校失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除学校信息")
    @CrossOrigin
    @RequestMapping(value= "/school/deleteSchool",method= RequestMethod.POST)
    @ApiOperation(value = "deleteSchool（删除学校信息）",notes = "删除学校信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteSchool(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除学校接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            schoolService.deleteSchool(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除学校成功");
            logger.info("删除学校成功");
        }catch (Exception e){
            logger.error("调用删除学校接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/school/listSchools",method= RequestMethod.POST)
    @ApiOperation(value = "listSchools（分页查询学校信息）",notes = "分页查询学校信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = SchoolView.class)
    })
    @ResponseBody
    public ResultView listSchools(@RequestBody SchoolForm schoolForm){
        logger.info("开始调用分页查询学校信息接口："+schoolForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<SchoolView> pageInfo = schoolService.listSchoolView(schoolForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询学校信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
