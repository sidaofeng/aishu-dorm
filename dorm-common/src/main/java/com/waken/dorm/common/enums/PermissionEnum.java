package com.waken.dorm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName: PermissionEnum
 * @Description: 权限枚举
 * @Author: zhaoRong
 * @Create: 2019/12/9 21:05
 **/
@Getter
@ToString
@AllArgsConstructor
public enum PermissionEnum {
    ADD("新增", "add"),
    DELETE("删除", "delete"),
    UPDATE("修改", "update"),
    LIST("集合", "list"),
    PAGE("分页", "page"),
    IMPORT("导入", "import"),
    EXPORT("导出", "export"),
    VIEW("查看", "view"),
    SAVE("保存", "save");

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
}
