package com.waken.dorm.controller.common;

import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CommonController
 * @Description 后台shiro权限控制相关接口
 * @Author zhaoRong
 * @Date 2019/4/9 19:08
 **/
@Controller
public class CommonController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 未授权跳转方法
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = {"/unAuth"},method = RequestMethod.GET)
    @ResponseBody
    public ResultView unAuth(){
        ResultView resultView = new ResultView();
        Subject subject = SecurityUtils.getSubject();
        try {
            logger.info("开始进入未授权跳转方法");
            subject.logout();
            resultView.setCode(ResultEnum.UN_AUTH.getCode());
            resultView.setMsg("未授权，请跳转至登录页面！");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("subject.logout()失败原因是:"+e.getMessage());
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("subject.logout()失败");
        }
        return resultView;
    }

    /**
     * 被踢出后跳转方法
     * @return
     */
    @CrossOrigin
    @RequestMapping(value= "/kickout",method= RequestMethod.GET)
    @ResponseBody
    public ResultView kickout(){
        ResultView resultView = new ResultView();
        logger.info("开始进入被踢出后跳转方法");
        resultView.setCode(ResultEnum.OTHER_LOGIN.getCode());
        resultView.setMsg("在其他设备登录，请跳转至登录页面！");
        return resultView;
    }
}
