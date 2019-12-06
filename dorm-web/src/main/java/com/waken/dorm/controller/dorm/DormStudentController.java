package com.waken.dorm.controller.dorm;

import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.form.dorm.DormStudentForm;
import com.waken.dorm.common.form.dorm.StudentBedForm;
import com.waken.dorm.common.view.dorm.DormStudentView;
import com.waken.dorm.service.dorm.DormStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DormStudentController
 * @Description: 给学生分配宿舍床位控制层
 * @Author: zhaoRong
 * @Create: 2019/12/5 20:52
 **/
@Slf4j
@Api(value = "学生宿舍床位分配", description = "宿舍管理-学生宿舍床位分配")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DormStudentController {
    private final DormStudentService dormStudentService;

    @CrossOrigin
    @PostMapping(value = "student/bed/page")
    @ApiOperation(value = "分页", notes = "分页")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormStudentView.class)
    })
    public AjaxResponse listDormScores(@RequestBody DormStudentForm form) {
        return AjaxResponse.success(this.dormStudentService.findPage(form));
    }

    @CrossOrigin
    @PostMapping(value = "student/bed/batch/add")
    @ApiOperation(value = "学生宿舍床位分配", notes = "学生宿舍床位分配")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormStudentView.class)
    })
    public AjaxResponse batchAdd(@RequestBody StudentBedForm form) {
        return AjaxResponse.success(this.dormStudentService.batchAdd(form));
    }
}
