package com.waken.dorm.dao.dict;

import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.entity.dict.DictExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.view.dict.DictView;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<DictView> listDicts(DictForm dictForm);

    List<Dict> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DictExample example);

    int deleteByExample(DictExample example);

    int deleteByPrimaryKey(String pkDictId);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(String pkDictId);

    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}