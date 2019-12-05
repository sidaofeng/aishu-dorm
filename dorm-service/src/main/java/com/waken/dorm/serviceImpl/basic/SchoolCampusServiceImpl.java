package com.waken.dorm.serviceImpl.basic;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.SchoolCampus;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.dao.dorm.SchoolCampusMapper;
import com.waken.dorm.service.basic.SchoolCampusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 校区基本数据表 服务实现类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Service
public class SchoolCampusServiceImpl extends ServiceImpl<SchoolCampusMapper, SchoolCampus> implements SchoolCampusService {
    @Override
    public int insert(SchoolCampus schoolCampus) {
        int result = 0;
        if (schoolCampus == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(schoolCampus) == 0) {
            result = this.baseMapper.insert(schoolCampus);
        }
        return result;
    }

    @Override
    public void delete(DeleteForm deleteForm) {
        if (deleteForm == null || deleteForm.getDelIds() == null) {
            throw new ServiceException("入参数据为空");
        }
        if (!deleteForm.getDelIds().isEmpty()) {
            //物理删除
            if (deleteForm.getDelStatus() == CodeEnum.YES.getCode()) {
                this.baseMapper.deleteBatchIds(deleteForm.getDelIds());
            } else if (deleteForm.getDelStatus() == CodeEnum.NO.getCode()) {
                List<SchoolCampus> schoolCampuses = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    SchoolCampus schoolCampus = new SchoolCampus();
                    schoolCampus.setId(id);
                    schoolCampus.setIsDeleted(true);
                    schoolCampuses.add(schoolCampus);
                }
                this.updateBatchById(schoolCampuses);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(SchoolCampus schoolCampus) {
        int result = 0;
        if (schoolCampus == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(schoolCampus) == 0) {
            result = this.baseMapper.updateById(schoolCampus);
        }
        return result;
    }

    @Override
    public SchoolCampus get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<SchoolCampus> list() {
        List<SchoolCampus> schoolCampuses = this.baseMapper.selectList(new LambdaQueryWrapper<SchoolCampus>()
                .eq(SchoolCampus::getIsDeleted, 0)
                .orderByDesc(SchoolCampus::getCreateTime));
        return schoolCampuses;
    }

    private int verification(SchoolCampus schoolCampus) {
        if (schoolCampus.getName() != null) {
            //验证校区号
            List<SchoolCampus> schoolCampuses = this.baseMapper.selectList(new LambdaQueryWrapper<SchoolCampus>()
                    .eq(SchoolCampus::getName, schoolCampus.getName())
                    .eq(SchoolCampus::getIsDeleted, 0));
            if (schoolCampuses.size() != 0) {
                if (schoolCampus.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (SchoolCampus campus : schoolCampuses) {
                        if (!campus.getId().equals(schoolCampus.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (schoolCampus.getCode() != null) {
            //验证校区名称
            List<SchoolCampus> schoolCampuses = this.baseMapper.selectList(new LambdaQueryWrapper<SchoolCampus>()
                    .eq(SchoolCampus::getCode, schoolCampus.getCode())
                    .eq(SchoolCampus::getIsDeleted, 0));
            if (schoolCampuses.size() != 0) {
                if (schoolCampus.getId() == null) {
                    throw new ServiceException("校区号不能重复");
                } else {
                    for (SchoolCampus campus : schoolCampuses) {
                        if (!campus.getId().equals(schoolCampus.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("校区号不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }


}
