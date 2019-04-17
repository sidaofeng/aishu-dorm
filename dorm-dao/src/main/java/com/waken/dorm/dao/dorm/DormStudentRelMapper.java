package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormStudentRel;
import com.waken.dorm.common.entity.dorm.DormStudentRelExample;
import java.util.List;

import com.waken.dorm.common.view.dorm.DormStudentRelView;
import org.apache.ibatis.annotations.Param;

public interface DormStudentRelMapper {
    List<DormStudentRelView> listDormStudentRelView(String dormId);

    int batchAddDormStudentRel(List<DormStudentRel> dormStudentRelList);

    int countByExample(DormStudentRelExample example);

    int deleteByExample(DormStudentRelExample example);

    int deleteByPrimaryKey(String pkDormStudentId);

    int insert(DormStudentRel record);

    int insertSelective(DormStudentRel record);

    List<DormStudentRel> selectByExample(DormStudentRelExample example);

    DormStudentRel selectByPrimaryKey(String pkDormStudentId);

    int updateByExampleSelective(@Param("record") DormStudentRel record, @Param("example") DormStudentRelExample example);

    int updateByExample(@Param("record") DormStudentRel record, @Param("example") DormStudentRelExample example);

    int updateByPrimaryKeySelective(DormStudentRel record);

    int updateByPrimaryKey(DormStudentRel record);
}