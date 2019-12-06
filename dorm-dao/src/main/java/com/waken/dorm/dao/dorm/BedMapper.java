package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.dorm.DormStudentForm;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.dorm.DormStudentView;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

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

    /**
     * 查询校区、建筑、楼层等基础数据的树形对象集合
     *
     * @return
     */
    List<Tree<T>> selectBasicTreeList();

    /**
     * 查询宿舍房间的树形对象集合
     *
     * @return
     */
    List<Tree<T>> selectDormRoomTreeList();

    /**
     * 查询宿舍床位的树形对象集合
     *
     * @return
     */
    List<Tree<T>> selectBedTreeList();

}
