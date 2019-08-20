package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormRuleService;
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
 * @ClassName DormRuleController
 * @Description 后台管理宿舍规则相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 11:17
 **/
@Slf4j
@Api(value = "后台管理宿舍规则相关接口", description = "后台管理宿舍规则相关接口(AiShu)")
@RestController
public class DormRuleController extends BaseController {
    @Autowired
    private DormRuleService dormRuleService;

    @Log("(保存/修改)宿舍规则信息")
    @CrossOrigin
    @PostMapping(value = "rule/save")
    @ApiOperation(value = "(保存/修改)宿舍规则信息", notes = "(保存/修改)宿舍规则信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRule.class)
    })
    public ResultView saveDormRule(@RequestBody EditDormRuleForm editDormRuleForm) {
        log.info("开始调用(保存/修改)宿舍规则信息接口接口：" + editDormRuleForm.toString());
        return ResultUtil.success(dormRuleService.saveDormRule(editDormRuleForm));
    }

    @Log("删除宿舍规则信息")
    @CrossOrigin
    @DeleteMapping(value = "rule/delete")
    @ApiOperation(value = "删除宿舍规则信息", notes = "删除宿舍规则信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteDormRule(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍规则接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return ResultUtil.errorByMsg("入参为空！");
        }
        dormRuleService.deleteDormRule(deleteFrom);
        return ResultUtil.success();
    }

    @CrossOrigin
    @PostMapping(value = "rule/page")
    @ApiOperation(value = "分页查询宿舍规则信息", notes = "分页查询宿舍规则信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRuleView.class)
    })
    public ResultView listDormRules(@RequestBody DormRuleForm RuleForm) {
        log.info("开始调用分页查询宿舍规则信息接口：" + RuleForm.toString());
        RuleForm.setTerminal(CodeEnum.WEB.getCode());
        return ResultUtil.success(dormRuleService.listDormRules(RuleForm));
    }
}
