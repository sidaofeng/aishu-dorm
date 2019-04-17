package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.utils.redis.RedisUtils;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.service.dorm.DormService;
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
 * @ClassName AppDormController
 * @Description APP端宿舍相关接口
 * @Author zhaoRong
 * @Date 2019/4/3 21:07
 **/
@Api(value = "APP端宿舍相关接口", description = "APP端宿舍相关接口(赵荣)")
@RestController
public class AppDormController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DormService dormService;

    @RequestMapping(value= "/dorm/queryAppDormView",method= RequestMethod.POST)
    @ApiOperation(value = "queryAppDormView（app查询宿舍信息）",notes = "app查询宿舍信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AppDormView.class)
    })
    @ResponseBody
    public ResultView queryAppDormView(@RequestHeader HttpHeaders header, String studentToken){
        ResultView redisResult = redisUtils.getStudentInfo(header, studentToken);
        StudentInfo studentInfo = (StudentInfo) redisResult.getData();
        if (studentInfo == null) {
            return redisResult;
        }
        String studentId = studentInfo.getStudentId();
        logger.info("开始调用app查询宿舍信息接口："+studentId);
        ResultView resultView = new ResultView();
        try{
            AppDormView appDormView = dormService.queryAppDormView(studentId);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(appDormView);
        }catch (Exception e){
            logger.error("调用app查询宿舍信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
