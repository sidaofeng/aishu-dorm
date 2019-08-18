package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormViolationMapper extends BaseMapper<DormViolation> {
    int batchUpdateStatus(Map<String, Object> param);

    List<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm);

    List<DormViolationView> listDormViolations(DormViolationForm dormViolationForm);

    List<DormViolation> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}