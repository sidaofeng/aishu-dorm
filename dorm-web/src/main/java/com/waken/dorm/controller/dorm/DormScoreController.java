package com.waken.dorm.controller.dorm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.FileUtils;
import com.waken.dorm.common.view.dorm.DormScoreView;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.dorm.DormScoreService;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DormScoreController
 * @Description 宿舍评分
 * @Author zhaoRong
 * @Date 2019/3/31 20:54
 **/
@Slf4j
@Api(value = "宿舍评分管理", description = "宿舍管理-宿舍评分管理")
@RestController
public class DormScoreController extends BaseController {
    @Autowired
    private DormScoreService dormScoreService;

    /**
     * 新增
     *
     * @param score
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "score/add")
    @ApiOperation(value = "新增", notes = "新增 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse insert(@RequestBody DormScore score) {
        if (this.dormScoreService.insert(score) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }
    }

    /**
     * 删除
     *
     * @param deleteForm
     */
    @CrossOrigin
    @DeleteMapping(value = "score/delete")
    @ApiOperation(value = "删除", notes = "删除 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse delete(@RequestBody DeleteForm deleteForm) {
        this.dormScoreService.delete(deleteForm);
        return AjaxResponse.success();
    }

    /**
     * 更新
     *
     * @param score
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "score/update")
    @ApiOperation(value = "更新", notes = "更新 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse update(@RequestBody DormScore score) {
        if (this.dormScoreService.update(score) == 1) {
            return AjaxResponse.success();
        } else {
            return AjaxResponse.error();
        }

    }

    /**
     * 通过id获取评分信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "score/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormScore.class)
    })
    public AjaxResponse get(@PathVariable("id") String id) {
        return AjaxResponse.success(this.dormScoreService.get(id));
    }


    @CrossOrigin
    @PostMapping(value = "score/page")
    @ApiOperation(value = "分页", notes = "分页")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormScoreView.class)
    })
    public AjaxResponse listDormScores(@RequestBody ListDormScoreForm listDormScoreForm) {
        return AjaxResponse.success(dormScoreService.findPage(listDormScoreForm));
    }

    @Log("批量导入宿舍评分记录（excel）")
    @CrossOrigin
    @PostMapping(value = "score/import")
    @ApiOperation(value = "批量导入宿舍评分记录（excel）", notes = "批量导入宿舍评分记录（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse batchImportScore(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            FileUtils.checkFile(file);
            long beginMillis = System.currentTimeMillis();
            List<DormScore> successList = Lists.newArrayList();
            List<Map<String, Object>> errorList = Lists.newArrayList();
            ExcelKit.$Import(DormScore.class)
                    .readXlsx(file.getInputStream(), new ExcelReadHandler<DormScore>() {
                        @Override
                        public void onSuccess(int sheetIndex, int rowIndex, DormScore dormScore) {
                            successList.add(dormScore);
                        }

                        @Override
                        public void onError(int sheetIndex, int rowIndex, List<ExcelErrorField> list) {
                            errorList.add(ImmutableMap
                                    .of("sheetIndex", sheetIndex, "rowIndex", rowIndex, "list", list));
                        }
                    });
            long time = (System.currentTimeMillis() - beginMillis) / 1000L;
            if (!errorList.isEmpty()) {
                return AjaxResponse.error(ImmutableMap.of("timeConsuming", time, "errorList", errorList));
            }
            if (!successList.isEmpty()) {
                dormScoreService.batchInsert(successList);
            }
            return AjaxResponse.success(ImmutableMap.of("timeConsuming", time));
        } catch (Exception e) {
            log.info("批量导入宿舍评分记录（excel）失败原因：" + e.getMessage());
            return AjaxResponse.error("批量导入宿舍评分记录（excel）失败");
        }
    }
}
