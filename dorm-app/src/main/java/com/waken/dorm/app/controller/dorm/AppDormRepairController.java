package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.service.dorm.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AppDormRepairController
 * @Description APP端宿舍维修
 * @Author zhaoRong
 * @Date 2019/4/2 10:07
 **/
@Slf4j
@Api(value = "APP端宿舍维修", description = "APP端宿舍维修")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppDormRepairController extends AppBaseController {
    private final RepairService repairService;
    private final StudentManager studentManager;

    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增", notes = "新增")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestParam DormRepair repair) {
        repair.setStudentCode(studentManager.getCurrentStudentCode());
        try {
            return AjaxResponse.success(repairService.insert(repair));
        } catch (Exception e) {
            log.info("新增宿舍维修记录失败原因：" + e.getMessage());
            return AjaxResponse.error();
        }
    }

    @GetMapping(value = "repair/page")
    @ApiOperation(value = "分页", notes = "分页 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public AjaxResponse findPage(DormRepairForm dormRepairForm) {
        dormRepairForm.setStudentId(studentManager.getCurrentStudentId());
        return AjaxResponse.success(repairService.findPage(dormRepairForm));
    }
}
