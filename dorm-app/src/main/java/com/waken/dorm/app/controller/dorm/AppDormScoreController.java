package com.waken.dorm.app.controller.dorm;

import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.service.dorm.DormScoreService;
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
 * @ClassName DormScoreController
 * @Description APP端宿舍评分相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 21:07
 **/
@Slf4j
@RestController
@Api(value = "APP端宿舍评分相关接口", description = "APP端宿舍评分相关接口(AiShu)")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppDormScoreController extends AppBaseController {

    private final DormScoreService dormScoreService;
    private final StudentManager studentManager;

    @GetMapping(value = "score/page")
    @ApiOperation(value = "分页查询学生对应宿舍的评分", notes = "分页查询学生对应宿舍的评分 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormScoreView.class)
    })
    public AjaxResponse appListDormScoreViews(Integer pageNum, Integer pageSize) {
        String studentId = studentManager.getCurrentStudentId();
        log.info("开始调用分页查询学生对应宿舍的评分接口：" + studentId);
        ListDormScoreForm listDormScoreForm = new ListDormScoreForm();
        if (null != pageNum) {
            listDormScoreForm.setPageNum(pageNum);
        }
        if (null != pageSize) {
            listDormScoreForm.setPageSize(pageSize);
        }
        listDormScoreForm.setStudentId(studentId);
        return AjaxResponse.success(dormScoreService.appListDormScoreViews(listDormScoreForm));
    }
}
