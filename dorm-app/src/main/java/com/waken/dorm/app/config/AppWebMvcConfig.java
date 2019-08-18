package com.waken.dorm.app.config;

import com.waken.dorm.app.interceptor.AppInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description App端web mvc 拦截器配置
 * @Author zhaoRong
 * @Date 2019/8/18 14:01
 **/
@Slf4j
@Configuration
public class AppWebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.initAppInterceptor()).addPathPatterns("/waken/app/dorm/**");
    }

    @Bean
    public AppInterceptor initAppInterceptor(){
        return new AppInterceptor();
    }
}
