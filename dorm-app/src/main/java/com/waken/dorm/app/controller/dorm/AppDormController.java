package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.service.dorm.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AppDormController
 * @Description APP端宿舍相关接口
 * @Author zhaoRong
 * @Date 2019/4/3 21:07
 **/
@Slf4j
@Api(value = "APP端宿舍相关接口", description = "APP端宿舍相关接口(AiShu)")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppDormController extends AppBaseController {
    private final StudentManager studentManager;
    private final DormService dormService;

    @GetMapping("room/query")
    @ApiOperation(value = "queryAppDormView（app查询宿舍信息）", notes = "app查询宿舍信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormView.class)
    })
    public AjaxResponse queryAppDormView() {
        String studentId = studentManager.getCurrentStudentId();
        log.info("开始调用app查询宿舍信息接口：" + studentId);
        AppDormView appDormView = dormService.queryAppDormView(studentId);
        return AjaxResponse.success(appDormView);
    }
}
