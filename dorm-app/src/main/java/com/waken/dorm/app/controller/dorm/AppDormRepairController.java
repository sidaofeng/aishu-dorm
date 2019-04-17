package com.waken.dorm.app.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.app.controller.base.AppBaseController;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.utils.redis.RedisUtils;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.service.dorm.DormRepairService;
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
 * @ClassName AppDormRepairController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 10:07
 **/
@Api(value = "APP端宿舍维修相关接口", description = "APP端宿舍维修相关接口(赵荣)")
@RestController
public class AppDormRepairController extends AppBaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRepairService dormRepairService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DormService dormService;

    @RequestMapping(value = {"dorm/addDormRepair"},method = RequestMethod.POST)
    @ApiOperation(value = "addDormRepair（新增宿舍维修记录）",notes = "新增宿舍维修记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView addDormRepair(@RequestHeader HttpHeaders header,@RequestParam AddDormRepairForm addDormRepairForm){
        ResultView redisResult = redisUtils.getStudentInfo(header, addDormRepairForm.getStudentToken());
        StudentInfo studentInfo = (StudentInfo) redisResult.getData();
        if (studentInfo == null) {
            return redisResult;
        }
        addDormRepairForm.setStudentId(studentInfo.getStudentId());
        logger.info("开始调用新增宿舍维修记录接口："+addDormRepairForm.toString());
        ResultView resultView = new ResultView();
        try{
            DormRepair dormRepair = dormRepairService.addDormRepair(addDormRepairForm);
            logger.info("新增宿舍维修记录成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormRepair);
            resultView.setMsg("新增宿舍维修记录成功");
        }catch (Exception e){
            logger.info("新增宿舍维修记录失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("新增宿舍维修记录失败原因："+e.getMessage());
        }
        return resultView;
    }
    @RequestMapping(value= "/dorm/listDormRepairs",method= RequestMethod.POST)
    @ApiOperation(value = "listDormRepairs（分页查询宿舍维修信息）",notes = "分页查询宿舍维修信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormRepairView.class)
    })
    @ResponseBody
    public ResultView listDormRepairs(@RequestHeader HttpHeaders header,Integer pageNum,Integer pageSize,String studentToken){
        ResultView redisResult = redisUtils.getStudentInfo(header, studentToken);
        StudentInfo studentInfo = (StudentInfo) redisResult.getData();
        if (studentInfo == null) {
            return redisResult;
        }
        String studentId = studentInfo.getStudentId();
        logger.info("开始调用分页查询宿舍维修信息接口："+studentId);
        ResultView resultView = new ResultView();
        try{
            DormRepairForm dormRepairForm = new DormRepairForm();
            dormRepairForm.setStudentId(studentId);
            dormRepairForm.setPageNum(pageNum);
            dormRepairForm.setPageSize(pageSize);
            dormRepairForm.setTerminal(CodeEnum.APP.getCode());
            PageInfo<DormRepairView> pageInfo = dormRepairService.listDormRepairs(dormRepairForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍维修信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
