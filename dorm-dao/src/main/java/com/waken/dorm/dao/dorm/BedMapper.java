package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.dorm.DormStudentForm;
import com.waken.dorm.common.view.dorm.DormStudentView;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 宿舍床位 Mapper 接口
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface BedMapper extends BaseMapper<DormBed> {

    IPage<DormStudentView> findPage(Page page, @Param("form") DormStudentForm form);

}
