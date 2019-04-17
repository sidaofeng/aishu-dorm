package com.waken.dorm.common.utils.redis;

import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @ClassName RedisUtils
 * @Description RedisUtil
 * @Author zhaoRong
 * @Date 2019/3/30 14:13
 **/
@Service("redisUtils")
public class RedisUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisCacheManager redisCacheManager;

    public ResultView getStudentInfo(HttpHeaders header, String studentToken) {
        logger.info("redisUtils: 开始进入通过redis获取学生信息");
        ResultView resultView = new ResultView();
        if (StringUtils.isEmpty(studentToken)) {
            studentToken = header.getFirst(Constant.TOKEN);
            if (StringUtils.isEmpty(studentToken)) {
                throw new DormException("studentToken 为空！");
            }
        }
        boolean isExistsToken = redisCacheManager.exists(studentToken);
        if (isExistsToken == false) {
            logger.info("账号在其他设备登录");
            resultView.setCode(ResultEnum.OTHER_LOGIN.getCode());
            resultView.setMsg("您的账号在其他设备登录,如非本人操作,建议立即修改密码！");
            return resultView;
        }
        StudentInfo studentInfo = (StudentInfo) redisCacheManager.get(studentToken);
        if (studentInfo == null) {
            logger.info("登录超时");
            resultView.setCode(ResultEnum.TIME_OUT.getCode());
            resultView.setMsg("登录超时，请重新登录");
            return resultView;
        }
        resultView.setData(studentInfo);
        return resultView;
    }
    public ResultView delStudentInfo(HttpHeaders header, String studentToken){
        logger.info("redisUtils: 开始进入通过redis删除缓存学生信息");
        ResultView resultView = new ResultView();
        if (StringUtils.isEmpty(studentToken)) {
            studentToken = header.getFirst(Constant.TOKEN);
            if (StringUtils.isEmpty(studentToken)) {
                throw new DormException("studentToken 为空！");
            }
        }
        boolean isExistsToken = redisCacheManager.exists(studentToken);
        if (isExistsToken == false) {
            logger.info("账号在其他设备登录");
            resultView.setCode(ResultEnum.OTHER_LOGIN.getCode());
            resultView.setMsg("您的账号在其他设备登录,如非本人操作,建议立即修改密码！");
            return resultView;
        }
        try {
            redisCacheManager.delete(studentToken);
            logger.info("注销学生成功！");
            resultView.setCode(ResultEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("redis删除失败原因："+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
        }

        return resultView;
    }
}
