package com.waken.dorm.common.form.resource;

import lombok.Data;

/**
 * @ClassName: ButtonResources
 * @Description:
 * @Author: zhaoRong
 * @Create: 2019/11/20 23:20
 **/
@Data
public class ButtonResources {
    private String id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 权限
     */
    private String perms;
}
