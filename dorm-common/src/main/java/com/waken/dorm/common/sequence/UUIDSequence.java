package com.waken.dorm.common.sequence;

import com.waken.dorm.common.constant.Constant;

import java.util.UUID;

/**
 * UUID主键策略
 * @Author zhaoRong
 * @Date 2019/3/21 19:56
 **/
public class UUIDSequence {
    public UUIDSequence(){}
    /**
     * 生成32位的UUID
     * @return
     */
    public synchronized static String next() {
        return UUID.randomUUID().toString().replace(Constant.UNDER_LINE, Constant.NULL_STRING);
    }

    public static void main(String[] args) {
        System.out.println(UUIDSequence.next());
    }
}
