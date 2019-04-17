package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.DozerMapper;
import com.waken.dorm.common.view.dorm.DormScoreView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName DormScoreController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/31 20:54
 **/
@Api(value = "后台管理宿舍评分模块相关接口", description = "后台管理宿舍评分模块相关接口(赵荣)")
@Controller
public class DormScoreController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormScoreService dormScoreService;
    @Log("批量导入宿舍评分记录（excel）")
    @CrossOrigin
    @RequestMapping(value = {"dorm/batchAddDormScore"},method = RequestMethod.POST)
    @ApiOperation(value = "batchAddDormScore（批量导入宿舍评分记录（excel））",notes = "批量导入宿舍评分记录（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView batchAddDormScore(@RequestParam(value = "file", required = false) MultipartFile file){
        logger.info("开始调用批量导入宿舍评分记录（excel）接口："+file.getOriginalFilename().toString());
        ResultView resultView = new ResultView();
        try{
            dormScoreService.batchAddDormScore(file);
            logger.info("批量导入宿舍评分记录（excel）成功");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("批量导入宿舍评分记录（excel）成功");
        }catch (Exception e){
            logger.info("批量导入宿舍评分记录（excel）失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("批量导入宿舍评分记录（excel）失败原因："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍评分信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDormScore",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDormScore（删除宿舍评分信息）",notes = "删除宿舍评分信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDormScore(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍评分信息接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dormScoreService.deleteDormScore(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍评分信息成功");
            logger.info("删除宿舍评分信息成功");
        }catch (Exception e){
            logger.error("调用删除宿舍评分信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDormScores",method= RequestMethod.POST)
    @ApiOperation(value = "listDormScores（分页查询宿舍评分信息）",notes = "分页查询宿舍评分信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormScoreView.class)
    })
    @ResponseBody
    public ResultView listDormScores(@RequestBody ListDormScoreForm listDormScoreForm){
        logger.info("开始调用分页查询宿舍评分信息接口："+listDormScoreForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<DormScoreView> pageInfo = dormScoreService.listDormScores(listDormScoreForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍评分信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @Log("修改宿舍评分")
    @CrossOrigin
    @RequestMapping(value = "/dorm/updateDormScore", method = RequestMethod.POST)
    @ApiOperation(value = "updateDormScore（修改宿舍评分）", notes = "修改宿舍评分 ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = DormScore.class) })
    @ResponseBody
    public ResultView updateDormScore(@RequestBody DormScoreForm dormScoreForm) {
        logger.info("开始调用修改宿舍评分接口：" + dormScoreForm.toString());
        ResultView resultView = new ResultView();
        try {
            DormScore dormScore = dormScoreService.updateDormScore(dormScoreForm);
            resultView.setMsg("修改宿舍评分成功");
            resultView.setData(dormScore);
        } catch (Exception e) {
            logger.error("修改宿舍评分失败，原因 :" + e.getMessage());
            e.printStackTrace();
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("修改宿舍评分失败");
        }
        return resultView;
    }
}
