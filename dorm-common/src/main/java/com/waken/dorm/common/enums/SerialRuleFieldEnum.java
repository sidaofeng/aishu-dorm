package com.waken.dorm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 自动生成编码字段信息枚举
 * @Author: zhaoRong
 * @Create: 2019/12/8 17:10
 **/
@Getter
@ToString
@AllArgsConstructor
public enum SerialRuleFieldEnum {
    /**
     * 自动生成班级名称所需要的字段信息
     */
    CN_GN("年级名称", "gradeName", "className"),
    CN_MN("专业名称", "majorName", "className"),
    CN_AB("专业简称", "abbreviation", "className"),
    CN_ES("学制", "eduSystem", "className"),
    /**
     * 自动生成班级代码所需要的字段信息
     */
    CC_GC("年级代码", "gradeCode", "classCode"),
    CC_ES("学制", "eduSystem", "classCode"),
    CC_MN("专业编号", "majorNum", "classCode"),
    CC_MC("专业代码", "majorCode", "classCode"),
    /**
     * 自动生成学号所需要的字段信息
     */
    SNO_GC("年级代码", "gradeCode", "sno"),
    SNO_CC("班级代码", "classCode", "sno"),
    SNO_ES("学制", "eduSystem", "sno"),
    SNO_MN("专业编号", "majorNum", "sno"),
    SNO_MC("专业代码", "majorCode", "sno"),
    /**
     * 自动生成宿舍所需要的字段信息
     */
    DM_BC("楼栋编号", "buildingCode", "dorm"),
    DM_FC("楼层编号", "floorCode", "dorm");

    /**
     * 字段名称，自动生成所需要的字段
     */
    private String name;
    /**
     * 字段标识
     */
    private String code;
    /**
     * 类型，表示生成为哪个生成编码，
     * className->自动生成班级名称
     * classCode->自动生成班级编码
     */
    private String type;

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, List<SerialRuleFieldEnum>> collect = Arrays.stream(SerialRuleFieldEnum.values())
                .collect(Collectors.groupingBy(SerialRuleFieldEnum::getType, Collectors.toList()));
        List<SerialRuleFieldEnum> collect1 = Arrays.stream(SerialRuleFieldEnum.values())
                .filter(serialRuleFieldEnum -> "classCode".equals(serialRuleFieldEnum.getType())).collect(Collectors.toList());
        System.out.println(collect);
    }
}
