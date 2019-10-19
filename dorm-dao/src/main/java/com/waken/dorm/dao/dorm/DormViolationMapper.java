package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormViolationMapper extends BaseMapper<DormViolation> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<AppDormViolationView> appListDormViolations(Page page,@Param("form") DormViolationForm dormViolationForm);

    IPage<DormViolationView> listDormViolations(Page page,@Param("form") DormViolationForm dormViolationForm);

    List<DormViolation> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}