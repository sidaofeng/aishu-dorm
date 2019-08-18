package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.DormStudentRel;
import com.waken.dorm.common.view.dorm.DormStudentRelView;

import java.util.List;

public interface DormStudentRelMapper extends BaseMapper<DormStudentRel> {
    List<DormStudentRelView> listDormStudentRelView(String dormId);

    int batchAddDormStudentRel(List<DormStudentRel> dormStudentRelList);
}