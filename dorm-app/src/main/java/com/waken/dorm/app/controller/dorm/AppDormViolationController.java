package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.utils.redis.RedisUtils;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.service.dorm.DormViolationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AppDormViolationController
 * @Description APP端宿舍违规相关接口
 * @Author zhaoRong
 * @Date 2019/4/2 13:43
 **/
@Api(value = "APP端宿舍违规相关接口", description = "APP端宿舍违规相关接口(赵荣)")
@RestController
public class AppDormViolationController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormViolationService dormViolationService;

    @Autowired
    RedisUtils redisUtils;
    @RequestMapping(value= "/dorm/listDormViolations",method= RequestMethod.POST)
    @ApiOperation(value = "listDormViolations（分页查询宿舍违规信息）",notes = "分页查询宿舍违规信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormViolationView.class)
    })
    @ResponseBody
    public ResultView listDormViolations(@RequestHeader HttpHeaders header,Integer pageNum,Integer pageSize, String studentToken){
        ResultView redisResult = redisUtils.getStudentInfo(header, studentToken);
        StudentInfo studentInfo = (StudentInfo) redisResult.getData();
        if (studentInfo == null) {
            return redisResult;
        }
        String studentId = studentInfo.getStudentId();
        logger.info("开始调用分页查询宿舍违规信息接口："+studentId);
        ResultView resultView = new ResultView();
        try{
            DormViolationForm dormViolationForm = new DormViolationForm();
            dormViolationForm.setPageNum(pageNum);
            dormViolationForm.setPageSize(pageSize);
            dormViolationForm.setStudentId(studentId);
            PageInfo<AppDormViolationView> pageInfo = dormViolationService.appListDormViolations(dormViolationForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍违规信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
