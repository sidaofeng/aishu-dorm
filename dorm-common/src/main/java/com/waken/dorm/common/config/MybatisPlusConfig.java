package com.waken.dorm.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.waken.dorm.common.interceptor.MybatisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor =
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        // 攻击 SQL 阻断解析器、加入解析链
//        sqlParserList.add(new BlockAttackSqlParser());
//        paginationInterceptor.setSqlParserList(sqlParserList);
        return new PaginationInterceptor();
    }
//    /**
//     * 自动填充功能
//     * @return
//     */
//    @Bean
//    public GlobalConfig globalConfig() {
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(new MybatisPlusMetaHandler());
//        return globalConfig;
//    }

    /**
     * 自定义mybatis新增修改时的拦截器
     * @return
     */
    @Bean
    public MybatisInterceptor mybatisPlusInterceptor(){
        return new MybatisInterceptor();
    }

}
