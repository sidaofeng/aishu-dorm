package com.waken.dorm.controller.basic;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dict.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DictController
 * @Description 字典模块相关接口
 * @Author zhaoRong
 * @Date 2019/4/19 17:09
 **/
@Slf4j
@Api(value = "字典模块相关接口", description = "字典模块相关接口(AiShu)")
@RestController
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;

    @Log("(保存/修改)字典信息")
    @CrossOrigin
    @PostMapping(value = "dict/save")
    @ApiOperation(value = "(保存/修改)字典信息", notes = "(保存/修改)字典信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Dict.class)
    })
    public AjaxResponse saveDict(@RequestBody EditDictForm editDictForm) {
        log.info("开始调用(保存/修改)字典信息接口接口：" + editDictForm.toString());
        Dict dict = dictService.saveDict(editDictForm);
        return AjaxResponse.success(dict);
    }

    @Log("删除字典信息")
    @CrossOrigin
    @DeleteMapping(value = "dict/delete")
    @ApiOperation(value = "删除字典信息", notes = "删除字典信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDict(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除字典接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        dictService.deleteDict(deleteFrom);
        return AjaxResponse.success();
    }

}
