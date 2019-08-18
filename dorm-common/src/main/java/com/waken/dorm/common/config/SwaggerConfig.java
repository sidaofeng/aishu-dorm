package com.waken.dorm.common.config;

import com.waken.dorm.common.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/16 20:22
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String SWAGGER_SCAN_ADMIN_PACKAGE = "com.waken.dorm.controller";
    public static final String ADMIN_VERSION = "2.0.0";
    public static final String SWAGGER_SCAN_APP_PACKAGE = "com.waken.dorm.app.controller";
    public static final String APP_VERSION = "2.0.0";

    @Bean
    public Docket createAdminRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(Constant.USER_TOKEN)
                .description("宿舍管理系统PC端访问令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("宿舍管理系统PC端接口")
                .apiInfo(apiAdminInfo())
                .globalOperationParameters(pars)
//                .produces(Sets.newHashSet("application/octet-stream"))
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_ADMIN_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiAdminInfo() {
        return new ApiInfoBuilder()
                .title("宿舍管理系统PC端接口")//设置文档的标题
                .description("用于宿舍管理系统PC端开发接口实现的文档")//设置文档的描述
                .version(ADMIN_VERSION)//设置文档的版本信息
                .contact(new Contact("AiShu", "http://dorm.aishu.site", "2181250231@qq.com"))
                .build();
    }

    @Bean
    public Docket createAppRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(Constant.STUDENT_TOKEN)
                .description("宿舍管理系统APP端访问令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("宿舍管理系统移动端接口")
                .apiInfo(apiAppInfo())
                .globalOperationParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_APP_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiAppInfo() {
        return new ApiInfoBuilder()
                .title("宿舍管理系统APP端接口")//设置文档的标题
                .description("用于宿舍管理系统APP端开发接口实现的文档")//设置文档的描述
                .version(APP_VERSION)//设置文档的版本信息
                .contact(new Contact("AiShu", "http://dorm.aishu.site", "2181250231@qq.com"))
                .build();
    }
}


//    @Bean
//    public Docket restfulApi() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("Authentication")
//                .description("访问dorm令牌")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false).build();
//        pars.add(tokenPar.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("AiShu")
//                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(true)
//                .forCodeGeneration(false)
//                .globalOperationParameters(pars)
//                .pathMapping("/") // base，最终调用接口后会和paths拼接在一起
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.waken.dorm"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("宿舍管理系统接口文档")
//                .description("宿舍管理系统详细接口文档")
//                .license("")
//                .licenseUrl("")
//                .termsOfServiceUrl("")
//                .version("2.0")
//                .contact(new Contact("赵荣", "http://dorm.aishu.site", "2181250231"))
//                .build();
//    }
//}
