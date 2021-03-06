package com.waken.dorm.common.manager;

import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.HttpContextUtils;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RedisUtils
 * @Description RedisUtil
 * @Author zhaoRong
 * @Date 2019/3/30 14:13
 **/
@Slf4j
@Service
public class StudentManager {
    @Autowired
    RedisCacheManager redisCacheManager;

    /**
     * 获取当前登录学生的信息
     *
     * @return
     */
    public StudentInfo getCurrentStudentInfo() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String studentToken = request.getHeader(CacheConstant.STUDENT_TOKEN);
        StudentInfo studentInfo = (StudentInfo) redisCacheManager.get(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        return studentInfo;
    }

    /**
     * 获取到的当前登录的学生id
     *
     * @return
     */
    public String getCurrentStudentId() {
        return this.getCurrentStudentInfo().getId();

    }

    /**
     * 获取到的当前登录的学生id
     *
     * @return
     */
    public String getCurrentStudentCode() {
        return this.getCurrentStudentInfo().getCode();

    }

    /**
     * 删除缓存的学生信息
     */
    public void delCacheStudentInfo() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String studentToken = request.getHeader(CacheConstant.STUDENT_TOKEN);
        if (StringUtils.isEmpty(studentToken)) {
            throw new ServerException("studentToken 为空！");
        }
        boolean isExistsToken = redisCacheManager.exists(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        if (!isExistsToken) {
            throw new ServerException(ResultEnum.TIME_OUT);
        }
        try {
            redisCacheManager.delete(CacheConstant.STUDENT_CACHE_PREFIX + studentToken);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("redis删除失败原因：" + e.getMessage());
        }
    }
}
