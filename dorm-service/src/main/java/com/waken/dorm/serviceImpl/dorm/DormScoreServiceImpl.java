package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.StringUtils;
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

import java.util.ArrayList;
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
public class DormScoreServiceImpl extends ServiceImpl<DormScoreMapper, DormScore> implements DormScoreService {
    private final DormMapper dormMapper;

    @Override
    public int insert(DormScore score) {
        Assert.notNull(score.getDormCode());
        this.validateDorm(score.getDormCode());
        return this.baseMapper.insert(score);
    }

    @Override
    public void delete(DeleteForm deleteForm) {
        if (deleteForm == null || deleteForm.getDelIds() == null) {
            throw new ServiceException("入参数据为空");
        }
        if (!deleteForm.getDelIds().isEmpty()) {
            //物理删除
            if (deleteForm.getDelStatus() == CodeEnum.YES.getCode()) {
                this.baseMapper.deleteBatchIds(deleteForm.getDelIds());
            } else if (deleteForm.getDelStatus() == CodeEnum.NO.getCode()) {
                List<DormScore> scoreList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    DormScore score = new DormScore();
                    score.setId(id);
                    score.setIsDeleted(true);
                    scoreList.add(score);
                }
                this.updateBatchById(scoreList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(DormScore score) {
        if (score == null) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.updateById(score);
    }

    @Override
    public DormScore get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    /**
     * 分页查询宿舍评分
     *
     * @param listDormScoreForm
     * @return
     */
    @Override
    public IPage<DormScoreView> findPage(ListDormScoreForm listDormScoreForm) {

        return this.baseMapper.findPage(DataHandle.getWrapperPage(listDormScoreForm), listDormScoreForm);
    }

    @Override
    public void batchInsert(List<DormScore> dormScoreList) {
        Assert.notNull(dormScoreList, dormScoreList.isEmpty(), "参数为空！");
        for (DormScore dormScore : dormScoreList) {
            dormScore.setStatus(CodeEnum.ENABLE.getCode());
        }
        if (!this.saveBatch(dormScoreList)) {
            throw new ServerException("批量新增失败");
        }
    }

    private void validateDorm(String dormCode) {
        Dorm dorm = dormMapper.selectOne(new LambdaQueryWrapper<Dorm>()
                .eq(Dorm::getCode, dormCode)
        );
        if (null == dorm) {
            throw new ServerException("宿舍号不存在!");
        }
    }
}
