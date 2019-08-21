package com.waken.dorm.controller.dorm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.FileUtils;
import com.waken.dorm.common.base.AjaxResponse;
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
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/31 20:54
 **/
@Slf4j
@Api(value = "后台管理宿舍评分模块相关接口", description = "后台管理宿舍评分模块相关接口(AiShu)")
@RestController
public class DormScoreController extends BaseController {
    @Autowired
    DormScoreService dormScoreService;

    @Log("批量导入宿舍评分记录（excel）")
    @CrossOrigin
    @PostMapping(value = "score/import")
    @ApiOperation(value = "批量导入宿舍评分记录（excel）", notes = "批量导入宿舍评分记录（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse batchImportScore(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("开始调用批量导入宿舍评分记录（excel）接口：" + file.getOriginalFilename());
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
                dormScoreService.batchAddDormScore(successList);
            }
            return AjaxResponse.success(ImmutableMap.of("timeConsuming", time));
        } catch (Exception e) {
            log.info("批量导入宿舍评分记录（excel）失败原因：" + e.getMessage());
            return AjaxResponse.error("批量导入宿舍评分记录（excel）失败");
        }
    }

    @Log("删除宿舍评分信息")
    @CrossOrigin
    @DeleteMapping(value = "score/delete")
    @ApiOperation(value = "删除宿舍评分信息", notes = "删除宿舍评分信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse deleteDormScore(@RequestBody DeleteForm deleteFrom) {
        log.info("开始调用删除宿舍评分信息接口：" + deleteFrom.toString());
        if (null == deleteFrom.getDelIds() || deleteFrom.getDelIds().isEmpty()) {
            return AjaxResponse.error("入参为空！");
        }
        dormScoreService.deleteDormScore(deleteFrom);
        return AjaxResponse.success();
    }

    @CrossOrigin
    @PostMapping(value = "score/page")
    @ApiOperation(value = "分页查询宿舍评分信息", notes = "分页查询宿舍评分信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormScoreView.class)
    })
    public AjaxResponse listDormScores(@RequestBody ListDormScoreForm listDormScoreForm) {
        log.info("开始调用分页查询宿舍评分信息接口：" + listDormScoreForm.toString());
        return AjaxResponse.success(dormScoreService.listDormScores(listDormScoreForm));
    }

    @Log("修改宿舍评分")
    @CrossOrigin
    @PutMapping(value = "score/update")
    @ApiOperation(value = "修改宿舍评分", notes = "修改宿舍评分 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormScore.class)})
    public AjaxResponse updateDormScore(@RequestBody DormScoreForm dormScoreForm) {
        log.info("开始调用修改宿舍评分接口：" + dormScoreForm.toString());
        return AjaxResponse.success(dormScoreService.updateDormScore(dormScoreForm));
    }
}
