package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormMapper extends BaseMapper<Dorm> {

    int batchUpdateStatus(Map<String, Object> param);

    AppDormView queryAppDormView(String studentId);

    List<DormView> listDorms(DormForm dormForm);

    List<Dorm> selectByDormNums(@Param("dormNums") List<String> dormNums);//通过批量dormNums批量查询

    List<Dorm> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}