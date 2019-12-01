package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.service.dorm.DormService;
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
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 10:07
 **/
@Slf4j
@Api(value = "APP端宿舍维修相关接口", description = "APP端宿舍维修相关接口(AiShu)")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppDormRepairController extends AppBaseController {
    private final RepairService repairService;
    private final StudentManager studentManager;
    private final DormService dormService;

    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增宿舍维修记录", notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse addDormRepair(@RequestParam AddDormRepairForm addDormRepairForm) {
        addDormRepairForm.setStudentId(studentManager.getCurrentStudentId());
        log.info("开始调用新增宿舍维修记录接口：" + addDormRepairForm.toString());
        try {
            DormRepair dormRepair = repairService.addDormRepair(addDormRepairForm);
            log.info("新增宿舍维修记录成功");
            return AjaxResponse.success(dormRepair);
        } catch (Exception e) {
            log.info("新增宿舍维修记录失败原因：" + e.getMessage());
            return AjaxResponse.error();
        }
    }

    @GetMapping(value = "repair/page")
    @ApiOperation(value = "分页查询宿舍维修信息", notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public AjaxResponse listDormRepairs(Integer pageNum, Integer pageSize) {
        String studentId = studentManager.getCurrentStudentId();
        log.info("开始调用分页查询宿舍维修信息接口：" + studentId);
        DormRepairForm dormRepairForm = new DormRepairForm();
        dormRepairForm.setStudentId(studentId);
        dormRepairForm.setPageNum(pageNum);
        dormRepairForm.setPageSize(pageSize);
        dormRepairForm.setTerminal(CodeEnum.APP.getCode());
        return AjaxResponse.success(repairService.listDormRepairs(dormRepairForm));
    }
}
