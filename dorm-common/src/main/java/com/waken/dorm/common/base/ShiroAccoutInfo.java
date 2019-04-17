package com.waken.dorm.common.base;

import java.io.Serializable;

/**
 * @ClassName ShiroAccoutInfo
 * @Description ShiroAccoutInfo
 * @Author zhaoRong
 * @Date 2019/3/21 20:13
 **/
public class ShiroAccoutInfo implements Serializable {
    private static final long serialVersionUID = 4264819235553231201L;
    private String userId;

    private String mobile;

    private String userName;

    private String name;

    private Integer userType;

    private Integer agentLevel;

    private Integer areaId;

    private Integer cityId;

    private Integer provinceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}

