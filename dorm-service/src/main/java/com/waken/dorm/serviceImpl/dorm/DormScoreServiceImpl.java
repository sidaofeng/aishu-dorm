package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormScoreMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DormScoreServiceImpl
 * @Description DormScoreServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/31 19:45
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DormScoreServiceImpl implements DormScoreService {
    private final DormScoreMapper dormScoreMapper;
    private final DormMapper dormMapper;

    /**
     * 批量导入宿舍评分记录（excel）
     *
     * @param dormScoreList
     */
    @Transactional
    @Override
    public void batchAddDormScore(List<DormScore> dormScoreList) {
        Assert.notNull(dormScoreList,dormScoreList.isEmpty(),"参数为空！");
        for (DormScore dormScore : dormScoreList) {
            dormScore.setStatus(CodeEnum.ENABLE.getCode());
        }
        int count = dormScoreMapper.batchAddDormScore(dormScoreList);
        Assert.isFalse(count == Constant.ZERO);
    }

    /**
     * 删除评分记录
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormScore(DeleteForm deleteForm) {
        log.info("service: 删除积分开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormScore> dormScoreList = dormScoreMapper.selectBatchIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormScore dormScore : dormScoreList) {
                if (CodeEnum.ENABLE.getCode() == dormScore.getStatus()) {
                    sb.append(dormScore.getDormNum());
                }
            }
            Assert.isNull(sb.toString(),"以下宿舍评分处于生效中：" + sb.toString());
            //删除宿舍积分
            count = dormScoreMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode() == delStatus) {
            count = dormScoreMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 通过学生id查询对应宿舍的评分记录
     *
     * @param listDormScoreForm
     * @return
     */
    @Override
    public IPage<AppDormScoreView> appListDormScoreViews(ListDormScoreForm listDormScoreForm) {
        Page page  = new Page(listDormScoreForm.getPageNum(),listDormScoreForm.getPageSize());
        return dormScoreMapper.appListDormScoreView(page,listDormScoreForm);
    }

    /**
     * 分页查询宿舍评分
     *
     * @param listDormScoreForm
     * @return
     */
    @Override
    public IPage<DormScoreView> listDormScores(ListDormScoreForm listDormScoreForm) {
        log.info("service: 分页查询宿舍评分信息开始");
        return dormScoreMapper.listDormScores(DataHandle.getWrapperPage(listDormScoreForm),listDormScoreForm);
    }

    /**
     * 修改评分
     *
     * @param form
     * @return
     */
    @Transactional
    @Override
    public DormScore updateDormScore(DormScoreForm form) {
        Assert.notNull(form.getId());
        DormScore dormScore = new DormScore();
        BeanMapper.copy(form, dormScore);
        dormScoreMapper.updateById(dormScore);
        return dormScore;
    }
}
