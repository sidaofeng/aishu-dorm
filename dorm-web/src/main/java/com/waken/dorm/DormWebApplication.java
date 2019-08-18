package com.waken.dorm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@MapperScan(basePackages = "com.waken.dorm.dao")
public class DormWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormWebApplication.class, args);
    }

}
