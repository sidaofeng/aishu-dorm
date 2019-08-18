package com.waken.dorm.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName ShiroAccoutInfo
 * @Description ShiroAccoutInfo
 * @Author zhaoRong
 * @Date 2019/3/21 20:13
 **/
@Getter
@Setter
public class ShiroAccount implements Serializable {
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
}

