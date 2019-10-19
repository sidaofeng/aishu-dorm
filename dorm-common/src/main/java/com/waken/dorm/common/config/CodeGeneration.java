//package com.waken.dorm.common.config;
//
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
///**
// * @ClassName: CodeGeneration
// * @Description: 代码生成器
// * @Author zhaoRong
// * @Date 2019/8/3 0:16
// */
//public class CodeGeneration {
//
//    /**
//     * @param args
//     * @Title: main
//     * @Description: 生成
//     */
//    public static void main(String[] args) {
//        AutoGenerator mpg = new AutoGenerator();
//        // 选择 freemarker 引擎，默认 Velocity
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
////        gc.setOutputDir("F://dorm-gene");
////        gc.setFileOverride(true);
////        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
////        gc.setEnableCache(false);// XML 二级缓存
//////        gc.setBaseResultMap(true);// XML ResultMap
////        gc.setBaseColumnList(false);// XML columList
////        gc.setAuthor("zhaoRong");// 作者
//
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
////        gc.setControllerName("%sAction");
////        gc.setServiceName("%sService");
////        gc.setServiceImplName("%sServiceImpl");
////        gc.setMapperName("%sMapper");
////        gc.setXmlName("%sMapper");
////        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dorm?noAccessToProcedureBodies=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
//        mpg.setDataSource(dsc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix(new String[]{"rm_"});// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy.setInclude(new String[]{"rm_dict", "rm_dorm", "rm_dorm_building", "rm_dorm_repair", "rm_dorm_rule", "rm_dorm_score", "rm_dorm_student_rel",
//                "rm_dorm_violation", "rm_log", "rm_resource", "rm_role",
//                "rm_role_resource_rel", "rm_student", "rm_user", "rm_user_privilege"}); // 需要生成的表
//
////        strategy.setSuperServiceClass(null);
////        strategy.setSuperServiceImplClass(null);
////        strategy.setSuperMapperClass(null);
//
//        mpg.setStrategy(strategy);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.waken.dorm");
////        pc.setController("action");
////        pc.setService("service");
////        pc.setServiceImpl("serviceImpl");
////        pc.setMapper("dao");
//        pc.setEntity("common.entity");
////        pc.setXml("mapper");
//        mpg.setPackageInfo(pc);
//
//        // 执行生成
//        mpg.execute();
//
//    }
//
//}
