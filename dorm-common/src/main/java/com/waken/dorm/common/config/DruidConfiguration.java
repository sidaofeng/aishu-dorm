package com.waken.dorm.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DruidConfiguration
 * @Description 监控
 * @Author zhaoRong
 * @Date 2019/4/9 13:02
 **/
//@Configuration
public class DruidConfiguration {
//    /**
//     * 注册一个StatViewServlet
//     *
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean DruidStatViewServle() {
//
//        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//
//        //添加初始化参数：initParams
//
//        //白名单S
//        servletRegistrationBean.addInitParameter("allow", "");//允许所有
//        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//        servletRegistrationBean.addInitParameter("deny", "192.168.0.114");
//
//        //登录查看信息的账号密码.
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "12356789");
//
//        //是否能够重置数据.
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//        return servletRegistrationBean;
//    }
//
//    /**
//     * 注册一个：filterRegistrationBean
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean druidStatFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//
//        //添加过滤规则.
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        //添加不需要忽略的格式信息.
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//
//        return filterRegistrationBean;
//    }
}
