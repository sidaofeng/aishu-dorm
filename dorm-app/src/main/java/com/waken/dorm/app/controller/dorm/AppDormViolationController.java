package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.dorm.DormViolationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AppDormViolationController
 * @Description APP端宿舍违规相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 13:43
 **/
@Slf4j
@Api(value = "APP端宿舍违规相关接口", description = "APP端宿舍违规相关接口(AiShu)")
@RestController
public class AppDormViolationController extends AppBaseController {
    @Autowired
    DormViolationService dormViolationService;

    @Autowired
    StudentManager studentManager;

    @GetMapping(value = "violation/page")
    @ApiOperation(value = "分页查询宿舍违规信息", notes = "分页查询宿舍违规信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormViolationView.class)
    })
    public ResultView listDormViolations(Integer pageNum, Integer pageSize) {
        String studentId = studentManager.getCurrentStudentId();
        log.info("开始调用分页查询宿舍违规信息接口：" + studentId);
        DormViolationForm dormViolationForm = new DormViolationForm();
        dormViolationForm.setPageNum(pageNum);
        dormViolationForm.setPageSize(pageSize);
        dormViolationForm.setStudentId(studentId);
        PageInfo<AppDormViolationView> pageInfo = dormViolationService.appListDormViolations(dormViolationForm);
        return ResultUtil.success(pageInfo);
    }
}
