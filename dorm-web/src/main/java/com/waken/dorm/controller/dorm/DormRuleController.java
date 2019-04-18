package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormRuleService;
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
 * @ClassName DormRuleController
 * @Description 后台管理宿舍规则相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 11:17
 **/
@Api(value = "后台管理宿舍规则相关接口", description = "后台管理宿舍规则相关接口(赵荣)")
@Controller
public class DormRuleController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DormRuleService dormRuleService;
    @Log("(保存/修改)宿舍规则信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/saveDormRule",method= RequestMethod.POST)
    @ApiOperation(value = "saveDormRule（(保存/修改)宿舍规则信息）",notes = "(保存/修改)宿舍规则信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRule.class)
    })
    @ResponseBody
    public ResultView saveDormRule(@RequestBody EditDormRuleForm editDormRuleForm){
        logger.info("开始调用(保存/修改)宿舍规则信息接口接口："+editDormRuleForm.toString());
        ResultView resultView = new ResultView();
        try{
            DormRule dormRule = dormRuleService.saveDormRule(editDormRuleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormRule);
            resultView.setMsg("(保存/修改)宿舍规则信息成功");
            logger.info("(保存/修改)宿舍规则信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)宿舍规则信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)宿舍规则失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍规则信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDormRule",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDormRule（删除宿舍规则信息）",notes = "删除宿舍规则信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormRule(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍规则接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dormRuleService.deleteDormRule(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍规则成功");
            logger.info("删除宿舍规则成功");
        }catch (Exception e){
            logger.error("调用删除宿舍规则接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDormRules",method= RequestMethod.POST)
    @ApiOperation(value = "listDormRules（分页查询宿舍规则信息）",notes = "分页查询宿舍规则信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRuleView.class)
    })
    @ResponseBody
    public ResultView listDormRules(@RequestBody DormRuleForm RuleForm){
        logger.info("开始调用分页查询宿舍规则信息接口："+RuleForm.toString());
        ResultView resultView = new ResultView();
        try{
            RuleForm.setTerminal(CodeEnum.WEB.getCode());
            PageInfo<DormRuleView> pageInfo = dormRuleService.listDormRules(RuleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.info("调用分页查询宿舍规则信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
