package com.waken.dorm.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OssConstantConfig
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/10 8:42
 **/
@Configuration
@Getter
@Setter
public class OssConstantConfig {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.fileHost}")
    private String fileHost;

    @Value("${aliyun.oss.accessUrl}")
    private String accessUrl;
}
