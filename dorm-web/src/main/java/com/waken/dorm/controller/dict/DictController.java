package com.waken.dorm.controller.dict;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.view.dict.DictView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dict.DictService;
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
 * @ClassName DictController
 * @Description 字典模块相关接口
 * @Author zhaoRong
 * @Date 2019/4/19 17:09
 **/
@Api(value = "字典模块相关接口", description = "字典模块相关接口(赵荣)")
@Controller
public class DictController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DictService dictService;
    @Log("(保存/修改)字典信息")
    @CrossOrigin
    @RequestMapping(value= "/dict/saveDict",method= RequestMethod.POST)
    @ApiOperation(value = "saveDict（(保存/修改)字典信息）",notes = "(保存/修改)字典信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dict.class)
    })
    @ResponseBody
    public ResultView saveDict(@RequestBody EditDictForm editDictForm){
        logger.info("开始调用(保存/修改)字典信息接口接口："+editDictForm.toString());
        ResultView resultView = new ResultView();
        try{
            Dict dict = dictService.saveDict(editDictForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(dict);
            resultView.setMsg("(保存/修改)字典信息成功");
            logger.info("(保存/修改)字典信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("调用(保存/修改)字典信息接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("(保存/修改)字典失败："+e.getMessage());
        }
        return resultView;
    }
    @Log("删除字典信息")
    @CrossOrigin
    @RequestMapping(value= "/dict/deleteDict",method= RequestMethod.POST)
    @ApiOperation(value = "deleteDict（删除字典信息）",notes = "删除字典信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    @ResponseBody
    public ResultView deleteDict(@RequestBody DeleteForm deleteFrom){
        logger.info("开始调用删除字典接口："+deleteFrom.toString());
        ResultView resultView = new ResultView();
        try{
            dictService.deleteDict(deleteFrom);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setMsg("删除字典成功");
            logger.info("删除字典成功");
        }catch (Exception e){
            logger.error("调用删除字典接口失败:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg(e.getMessage());
        }
        return resultView;
    }
    @CrossOrigin
    @RequestMapping(value= "/dict/listDicts",method= RequestMethod.POST)
    @ApiOperation(value = "listDicts（分页查询字典信息）",notes = "分页查询字典信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DictView.class)
    })
    @ResponseBody
    public ResultView listDicts(@RequestBody DictForm dictForm){
        logger.info("开始调用分页查询字典信息接口："+dictForm.toString());
        ResultView resultView = new ResultView();
        try{
            PageInfo<DictView> pageInfo = dictService.listDicts(dictForm);
            resultView.setCode(ResultEnum.SUCCESS.getCode());
            resultView.setData(pageInfo);
        }catch (Exception e){
            logger.error("调用分页查询字典信息失败:"+e.getMessage());
            resultView.setMsg(e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }
        return resultView;
    }
}
