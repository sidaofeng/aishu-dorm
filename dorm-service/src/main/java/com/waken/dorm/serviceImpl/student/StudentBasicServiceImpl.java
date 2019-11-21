package com.waken.dorm.serviceImpl.student;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.dao.student.StudentBasicMapper;
import com.waken.dorm.service.student.StudentBasicService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生基本数据表 服务实现类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Service
public class StudentBasicServiceImpl extends ServiceImpl<StudentBasicMapper, StudentBasic> implements StudentBasicService {

}
