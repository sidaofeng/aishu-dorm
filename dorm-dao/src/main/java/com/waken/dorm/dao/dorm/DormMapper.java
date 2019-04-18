package com.waken.dorm.dao.dorm;

import java.util.List;
import java.util.Map;

import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormExample;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormView;
import org.apache.ibatis.annotations.Param;

public interface DormMapper {

    int batchUpdateStatus(Map<String,Object> param);

    AppDormView queryAppDormView(String studentId);

    List<DormView> listDorms(DormForm dormForm);

    List<Dorm> selectByDormNums(@Param("dormNums") List<String> dormNums);//通过批量dormNums批量查询

    List<Dorm> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DormExample example);

    int deleteByExample(DormExample example);

    int deleteByPrimaryKey(String pkDormId);

    int insert(Dorm record);

    int insertSelective(Dorm record);

    List<Dorm> selectByExample(DormExample example);

    Dorm selectByPrimaryKey(String pkDormId);

    int updateByExampleSelective(@Param("record") Dorm record, @Param("example") DormExample example);

    int updateByExample(@Param("record") Dorm record, @Param("example") DormExample example);

    int updateByPrimaryKeySelective(Dorm record);

    int updateByPrimaryKey(Dorm record);
}