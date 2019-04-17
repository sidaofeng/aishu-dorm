package com.waken.dorm.common.config;

/**
 * @ClassName Config
 * @Description Config
 * @Author zhaoRong
 * @Date 2019/3/21 20:13
 **/
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
     

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Config() {
        setPlatform_type("1");
    }

    public String getPlatform_type() {
        return platform_type;
    }

    public void setPlatform_type(String platform_type) {
        this.platform_type = platform_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }


    public String getRegion_Id() {
        return region_Id;
    }

    public void setRegion_Id(String region_Id) {
        this.region_Id = region_Id;
    }
}
