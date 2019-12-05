package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.service.dorm.DormViolationService;
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
 * @ClassName AppDormViolationController
 * @Description APP端宿舍违规
 * @Author zhaoRong
 * @Date 2019/4/2 13:43
 **/
@Slf4j
@RestController
@Api(value = "APP端宿舍违规", description = "APP端宿舍违规")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppDormViolationController extends AppBaseController {

    private final DormViolationService dormViolationService;
    private final StudentManager studentManager;

    @GetMapping(value = "violation/page")
    @ApiOperation(value = "分页", notes = "分页 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormViolationView.class)
    })
    public AjaxResponse findPage(DormViolationForm dormViolationForm) {
        String studentId = studentManager.getCurrentStudentId();
        dormViolationForm.setStudentId(studentId);
        return AjaxResponse.success(dormViolationService.page(dormViolationForm));
    }
}
