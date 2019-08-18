package com.waken.dorm.controller.dorm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.FileUtils;
import com.waken.dorm.common.utils.ResultUtil;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "后台管理宿舍评分模块相关接口", description = "后台管理宿舍评分模块相关接口(AiShu)")
@RestController
public class DormScoreController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormScoreService dormScoreService;

    @Log("批量导入宿舍评分记录（excel）")
    @CrossOrigin
    @PostMapping(value = "score/import")
    @ApiOperation(value = "批量导入宿舍评分记录（excel）", notes = "批量导入宿舍评分记录（excel）")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView batchImportScore(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("开始调用批量导入宿舍评分记录（excel）接口：" + file.getOriginalFilename());
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
                return ResultUtil.error(ImmutableMap.of("timeConsuming", time, "errorList", errorList));
            }
            if (!successList.isEmpty()) {
                dormScoreService.batchAddDormScore(successList);
            }
            return ResultUtil.success(ImmutableMap.of("timeConsuming", time));
        } catch (Exception e) {
            logger.info("批量导入宿舍评分记录（excel）失败原因：" + e.getMessage());
            return ResultUtil.errorByMsg("批量导入宿舍评分记录（excel）失败");
        }
    }

    @Log("删除宿舍评分信息")
    @CrossOrigin
    @DeleteMapping(value = "score/delete")
    @ApiOperation(value = "删除宿舍评分信息", notes = "删除宿舍评分信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView deleteDormScore(@RequestBody DeleteForm deleteFrom) {
        logger.info("开始调用删除宿舍评分信息接口：" + deleteFrom.toString());
        try {
            dormScoreService.deleteDormScore(deleteFrom);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("调用删除宿舍评分信息接口失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @CrossOrigin
    @GetMapping(value = "score/page")
    @ApiOperation(value = "分页查询宿舍评分信息", notes = "分页查询宿舍评分信息 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = DormScoreView.class)
    })
    public ResultView listDormScores(ListDormScoreForm listDormScoreForm) {
        logger.info("开始调用分页查询宿舍评分信息接口：" + listDormScoreForm.toString());
        try {
            return ResultUtil.success(dormScoreService.listDormScores(listDormScoreForm));
        } catch (Exception e) {
            logger.error("调用分页查询宿舍评分信息失败:" + e.getMessage());
            return ResultUtil.error();
        }
    }

    @Log("修改宿舍评分")
    @CrossOrigin
    @PutMapping(value = "score/update")
    @ApiOperation(value = "修改宿舍评分", notes = "修改宿舍评分 ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success", response = DormScore.class)})
    public ResultView updateDormScore(@RequestBody DormScoreForm dormScoreForm) {
        logger.info("开始调用修改宿舍评分接口：" + dormScoreForm.toString());
        try {
            return ResultUtil.success(dormScoreService.updateDormScore(dormScoreForm));
        } catch (Exception e) {
            logger.error("修改宿舍评分失败，原因 :" + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error();
        }
    }
}
