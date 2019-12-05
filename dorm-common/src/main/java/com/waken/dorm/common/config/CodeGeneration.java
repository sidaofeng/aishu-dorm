package com.waken.dorm.common.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.waken.dorm.common.base.BaseEntity;

/**
 * @ClassName: CodeGeneration
 * @Description: 代码生成器
 * @Author zhaoRong
 * @Date 2019/8/3 0:16
 */
public class CodeGeneration {

    /**
     * @param args
     * @Title: main
     * @Description: 生成
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F://dorm-gene");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("zhaoRong");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sAction");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dorm1201?noAccessToProcedureBodies=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"rm_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"rm_dorm_repair", "rm_dorm_score", "rm_dorm_violation"
        }); // 需要生成的表

//        strategy.setSuperServiceClass(null);
//        strategy.setSuperServiceImplClass(null);
//        strategy.setSuperMapperClass(null);
        strategy.setSuperEntityClass(BaseEntity.class);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.waken.dorm");
        pc.setController("controller.dorm");
        pc.setService("service.dorm");
        pc.setServiceImpl("serviceImpl.dorm");
        pc.setMapper("dao.dorm");
        pc.setEntity("common.entity.dorm");
        pc.setXml("mapper.dorm");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }

}
