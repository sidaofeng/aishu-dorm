package com.waken.dorm.controller.system;

import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.basic.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DictController
 * @Description 字典模块
 * @Author zhaoRong
 * @Date 2019/4/19 17:09
 **/
@Slf4j
@Api(value = "字典管理", description = "系统管理-字典管理")
@RestController
public class SystemDictController extends BaseController {
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
        return AjaxResponse.success(this.dictService.saveDict(editDictForm));
    }

    @Log("删除字典信息")
    @CrossOrigin
    @DeleteMapping(value = "dict/delete")
    @ApiOperation(value = "删除字典信息", notes = "删除字典信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDict(@RequestBody DeleteForm deleteFrom) {
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        dictService.deleteDict(deleteFrom);
        return AjaxResponse.success();
    }

    /**
     * 查询字典根节点的集合
     *
     * @param keywords
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "dict/roots")
    @ApiOperation(value = "查询字典根节点的集合", notes = "查询字典根节点的集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getDictRootList(String keywords) {
        return AjaxResponse.success(dictService.getDictRootList(keywords));
    }

    /**
     * 通过根节点编码查询字典树
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/tree/root/{code}")
    @ApiOperation(value = "通过字典跟节点编码查询字典树", notes = "通过字典跟节点编码查询字典树")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getTreeByRoot(@PathVariable("code") String rootCode) {
        return AjaxResponse.success(dictService.getTreeByRoot(rootCode));
    }

    /**
     * 通过根节点编码查询字典下一级的字典集合（只查下一级）
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/items/root/{code}")
    @ApiOperation(value = "通过根节点编码查询字典下一级的字典集合", notes = "通过根节点编码查询字典下一级的字典集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getDictItemsByRoot(@PathVariable("code") String rootCode) {
        if (StringUtils.isEmpty(rootCode)) {
            return AjaxResponse.error("根节点字典编码不能为空！");
        }
        return AjaxResponse.success(dictService.getDictItemsByRoot(rootCode));
    }

    /**
     * 通过父级ID编码查询字典下一级的字典集合（只查下一级）
     *
     * @return
     * @author aishu
     */
    @CrossOrigin
    @GetMapping(value = "dict/items/parent/{id}")
    @ApiOperation(value = "通过父级ID查询字典下一级的字典集合", notes = "通过父级ID查询字典下一级的字典集合")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse getDictItemsByParent(@PathVariable("id") String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return AjaxResponse.error("父级ID不能为空！");
        }
        return AjaxResponse.success(dictService.getDictItemsByParent(parentId));
    }

}
