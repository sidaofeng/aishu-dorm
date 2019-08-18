package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.dorm.DormService;
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
 * @ClassName AppDormController
 * @Description APP端宿舍相关接口
 * @Author zhaoRong
 * @Date 2019/4/3 21:07
 **/
@Api(value = "APP端宿舍相关接口", description = "APP端宿舍相关接口(AiShu)")
@RestController
public class AppDormController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentManager studentManager;
    @Autowired
    DormService dormService;

    @GetMapping("query")
    @ApiOperation(value = "queryAppDormView（app查询宿舍信息）", notes = "app查询宿舍信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormView.class)
    })
    public ResultView queryAppDormView() {
        String studentId = studentManager.getCurrentStudentId();
        logger.info("开始调用app查询宿舍信息接口：" + studentId);
        ResultView resultView = new ResultView();
        try {
            AppDormView appDormView = dormService.queryAppDormView(studentId);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(appDormView);
        } catch (Exception e) {
            logger.error("调用app查询宿舍信息失败:" + e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
