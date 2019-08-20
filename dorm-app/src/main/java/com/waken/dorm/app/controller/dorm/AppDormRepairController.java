package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.utils.ResultUtil;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.manager.StudentManager;
import com.waken.dorm.service.dorm.DormRepairService;
import com.waken.dorm.service.dorm.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class AppDormRepairController extends AppBaseController {
    @Autowired
    DormRepairService dormRepairService;
    @Autowired
    StudentManager studentManager;
    @Autowired
    DormService dormService;

    @PostMapping(value = "repair/add")
    @ApiOperation(value = "新增宿舍维修记录", notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView addDormRepair(@RequestParam AddDormRepairForm addDormRepairForm) {
        addDormRepairForm.setStudentId(studentManager.getCurrentStudentId());
        log.info("开始调用新增宿舍维修记录接口：" + addDormRepairForm.toString());
        ResultView resultView = new ResultView();
        try {
            DormRepair dormRepair = dormRepairService.addDormRepair(addDormRepairForm);
            log.info("新增宿舍维修记录成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormRepair);
            resultView.setMsg("新增宿舍维修记录成功");
        } catch (Exception e) {
            log.info("新增宿舍维修记录失败原因：" + e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("新增宿舍维修记录失败原因：" + e.getMessage());
        }
        return resultView;
    }

    @GetMapping(value = "repair/page")
    @ApiOperation(value = "分页查询宿舍维修信息", notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    public ResultView listDormRepairs(Integer pageNum, Integer pageSize) {
        String studentId = studentManager.getCurrentStudentId();
        log.info("开始调用分页查询宿舍维修信息接口：" + studentId);
        DormRepairForm dormRepairForm = new DormRepairForm();
        dormRepairForm.setStudentId(studentId);
        dormRepairForm.setPageNum(pageNum);
        dormRepairForm.setPageSize(pageSize);
        dormRepairForm.setTerminal(CodeEnum.APP.getCode());
        PageInfo<DormRepairView> pageInfo = dormRepairService.listDormRepairs(dormRepairForm);
        return ResultUtil.success(pageInfo);
    }
}
