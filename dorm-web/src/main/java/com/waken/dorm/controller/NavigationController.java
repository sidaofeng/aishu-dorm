package com.waken.dorm.controller;

import com.waken.dorm.controller.base.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName NavigationController
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/27 20:22
 **/
@Api(value = "后台导航管理")
@Controller
public class NavigationController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 进入登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin() {
        logger.info("开始转发到登录页面");
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    /**
     * 入口页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }


    /**================================== 用户模块 =====================================***/
    /**
     * 进入用户管理
     *
     * @return
     */
    @RequestMapping(value = "/userMng", method = RequestMethod.GET)
    public ModelAndView toUserMng() {
        ModelAndView mav = new ModelAndView("user/userMng");
        return mav;
    }
    /**
     * 进入角色管理
     *
     * @return
     */
    @RequestMapping(value = "/roleMng", method = RequestMethod.GET)
    public ModelAndView toRoleMng() {
        ModelAndView mav = new ModelAndView("user/roleMng");
        return mav;
    }
    /**
     * 进入资源管理
     *
     * @return
     */
    @RequestMapping(value = "/resourceMng", method = RequestMethod.GET)
    public ModelAndView toResourceMng() {
        ModelAndView mav = new ModelAndView("user/resourceMng");
        return mav;
    }
}
