package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.entity.dorm.DormViolationExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import org.apache.ibatis.annotations.Param;

public interface DormViolationMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm);

    List<DormViolationView> listDormViolations(DormViolationForm dormViolationForm);

    List<DormViolation> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DormViolationExample example);

    int deleteByExample(DormViolationExample example);

    int deleteByPrimaryKey(String pkDormViolationId);

    int insert(DormViolation record);

    int insertSelective(DormViolation record);

    List<DormViolation> selectByExample(DormViolationExample example);

    DormViolation selectByPrimaryKey(String pkDormViolationId);

    int updateByExampleSelective(@Param("record") DormViolation record, @Param("example") DormViolationExample example);

    int updateByExample(@Param("record") DormViolation record, @Param("example") DormViolationExample example);

    int updateByPrimaryKeySelective(DormViolation record);

    int updateByPrimaryKey(DormViolation record);
}