package com.waken.dorm.common.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Config
 * @Description Config
 * @Author zhaoRong
 * @Date 2019/3/21 20:13
 **/
@Getter
@Setter
public class Config {
    /**
     * 0代表web,1代表Android, 2 代表 iOS
     */
    private String platform_type;
    /**
     * 使用设备号/设备mac地址
     */
    private String client_id;
    /**
     * 服务端生成，验证请求合法性，登录有效期
     */
    private String token;
    /**
     * 移动端APP版本号
     */
    private String app_version;
    /**
     * 区域id
     */
    private String region_Id;
    /**
     * 用户id
     */
    private String uId;
    /**
     * 用户类型
     */
    private Integer userType;
}
