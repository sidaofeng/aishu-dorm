package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.DormScoreView;
import org.apache.ibatis.annotations.Param;

public interface DormScoreMapper extends BaseMapper<DormScore> {
    IPage<DormScoreView> findPage(Page page, @Param("form") ListDormScoreForm listDormScoreForm);
}