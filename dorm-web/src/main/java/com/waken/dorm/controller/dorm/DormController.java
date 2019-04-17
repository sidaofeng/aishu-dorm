package com.waken.dorm.controller.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DormController
 * @Description 宿舍相关接口"
 * @Author zhaoRong
 * @Date 2019/3/31 13:36
 **/
@Api(value = "宿舍模块相关接口", description = "宿舍模块相关接口(赵荣)")
@Controller
public class DormController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DormService dormService;
    @Log("(保存/修改)宿舍信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/saveDorm",method= RequestMethod.POST)
    @ApiOperation(value = "saveDorm（(保存/修改)宿舍信息）",notes = "(保存/修改)宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dorm.class)
    })
    @ResponseBody
    public ResultView saveDorm(@RequestBody EditDormForm editDormForm){
        logger.info("开始调用(保存/修改)宿舍信息接口接口："+editDormForm.toString());
        ResultView resultView = new ResultView();
        try{
            Dorm dorm = dormService.saveDorm(editDormForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dorm);
            resultView.setMsg("(保存/修改)宿舍信息成功");
            logger.info("(保存/修改)宿舍信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)宿舍信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)宿舍失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除宿舍信息")
    @CrossOrigin
    @RequestMapping(value= "/dorm/deleteDorm",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDorm（删除宿舍信息）",notes = "删除宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDorm(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除宿舍接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dormService.deleteDorm(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除宿舍成功");
            logger.info("删除宿舍成功");
        }catch (Exception e){
            logger.error("调用删除宿舍接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dorm/listDorms",method= RequestMethod.POST)
    @ApiOperation(value = "listDorms（分页查询宿舍信息）",notes = "分页查询宿舍信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormView.class)
    })
    @ResponseBody
    public ResultView listDorms(@RequestBody DormForm dormForm){
        logger.info("开始调用分页查询宿舍信息接口："+dormForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<DormView> pageInfo = dormService.listDorms(dormForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询宿舍信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value="/dorm/queryDormStudentsView",method= RequestMethod.POST)
    @ApiOperation(value = "queryDormStudentsView（查询宿舍与学生（关联与未关联）信息）",notes = "查询宿舍与学生（关联与未关联）信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormStudentsView.class)
    })
    @ResponseBody
    public ResultView queryDormStudentsView(@RequestParam(value = "dormId",required = false) String dormId){
        logger.info("开始调用查询宿舍与学生关联信息接口："+dormId);
        ResultView resultView = new ResultView();
        try{
            DormStudentsView dormStudentsView = dormService.queryDormStudentsView(dormId);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dormStudentsView);
            resultView.setMsg("查询宿舍与学生关联信息成功");
        }catch (Exception e){
            logger.error("调用查询宿舍与学生关联信息接口失败:",e);
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @Log("批量添加宿舍学生关联")
    @CrossOrigin
    @RequestMapping(value="/dorm/batchAddDormStudentRel",method= RequestMethod.POST)
    @ApiOperation(value = "batchAddDormStudentRel（批量添加宿舍学生关联）",notes = "批量添加宿舍学生关联 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView batchAddDormStudentRel(@RequestBody AddDormStudentRelForm addDormStudentRelForm){
        logger.info("开始调用批量添加宿舍学生关联接口："+addDormStudentRelForm.toString());
        ResultView resultView = new ResultView();
        try{
            dormService.batchAddDormStudentRel(addDormStudentRelForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("添加宿舍学生关联成功！");
        }catch (Exception e){
            logger.error("调用批量添加宿舍学生关联接口失败，原因:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
}
