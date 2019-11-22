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

    /**
     * 查询字典根节点的集合
     *
     * @param name
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "dict/roots/{name}")
    @ApiOperation(value = "查询字典根节点的集合", notes = "查询字典根节点的集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getDictRootList(@PathVariable(required = false) String name) {
        return AjaxResponse.success(dictService.getDictRootList(name));
    }

    /**
     * 通过父ID查询对应对应的字典树
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/tree/{parentId}")
    @ApiOperation(value = "通过父节点ID查询字典树", notes = "通过父节点ID查询字典树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getTreeByParentId(@PathVariable("parentId") String parentId) {
        return AjaxResponse.success(dictService.getTreeByParentId(parentId));
    }

    /**
     * 通过父节点编码查询字典树
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/tree/{parentCode}")
    @ApiOperation(value = "通过父节点编码查询字典树", notes = "通过父节点编码查询字典树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getTreeByParentCode(@PathVariable("parentCode") String parentCode) {
        return AjaxResponse.success(dictService.getTreeByParentCode(parentCode));
    }

    /**
     * 通过父节点编码查询字典下一级的字典集合（只查下一级）
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/list/{parentCode}")
    @ApiOperation(value = "通过父节点编码查询字典下一级的字典集合", notes = "通过父节点编码查询字典下一级的字典集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getDictByParentCode(@PathVariable("parentCode") String parentCode) {
        return AjaxResponse.success(dictService.getDictByParentCode(parentCode));
    }

}
