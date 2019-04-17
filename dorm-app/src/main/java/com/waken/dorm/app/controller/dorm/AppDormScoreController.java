package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.redis.RedisUtils;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.service.dorm.DormScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DormScoreController
 * @Description APP端宿舍评分相关接口
 * @Author zhaoRong
 * @Date 2019/3/31 21:07
 **/
@Api(value = "APP端宿舍评分相关接口", description = "APP端宿舍评分相关接口(赵荣)")
@RestController
public class AppDormScoreController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormScoreService dormScoreService;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value= "/dorm/appListDormScoreViews",method= RequestMethod.POST)
    @ApiOperation(value = "appListDormScoreViews（分页查询学生对应宿舍的评分）",notes = "分页查询学生对应宿舍的评分 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormScoreView.class)
    })
    @ResponseBody
    public ResultView appListDormScoreViews(@RequestHeader HttpHeaders header,Integer pageNum,Integer pageSize, String studentToken){
        ResultView redisResult = redisUtils.getStudentInfo(header, studentToken);
        StudentInfo studentInfo = (StudentInfo) redisResult.getData();
        if (studentInfo == null) {
            return redisResult;
        }
        String studentId = studentInfo.getStudentId();
        logger.info("开始调用分页查询学生对应宿舍的评分接口："+studentId);
        ResultView resultView = new ResultView();
        try{
            ListDormScoreForm listDormScoreForm = new ListDormScoreForm();
            listDormScoreForm.setPageNum(pageNum);
            listDormScoreForm.setPageSize(pageSize);
            listDormScoreForm.setStudentId(studentId);
            PageInfo<AppDormScoreView> pageInfo = dormScoreService.appListDormScoreViews(listDormScoreForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用查询学生对应宿舍的评分接口失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
