package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.dorm.DormRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AppDormRuleController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 11:21
 **/
@Api(value = "APP端宿舍规则相关接口", description = "APP端宿舍规则相关接口(AiShu)")
@RestController
public class AppDormRuleController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRuleService RuleService;
    @Autowired
    StudentManager studentManager;

    @GetMapping(value = "rule/page")
    @ApiOperation(value = "分页查询宿舍规则信息", notes = "分页查询宿舍规则信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRuleView.class)
    })
    public ResultView listDormRules(Integer pageNum, Integer pageSize) {
        String studentId = studentManager.getCurrentStudentId();
        logger.info("开始调用分页查询宿舍规则信息接口：" + studentId);
        ResultView resultView = new ResultView();
        try {
            DormRuleForm ruleForm = new DormRuleForm();
            ruleForm.setStudentId(studentId);
            ruleForm.setPageNum(pageNum);
            ruleForm.setPageSize(pageSize);
            ruleForm.setTerminal(CodeEnum.APP.getCode());
            PageInfo<DormRuleView> pageInfo = RuleService.listDormRules(ruleForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        } catch (Exception e) {
            logger.info("调用分页查询宿舍规则信息失败:" + e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
