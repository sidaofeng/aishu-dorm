package com.waken.dorm.common.utils;

import java.security.MessageDigest;

/**
 * @ClassName Md5Utils
 * @Description 加密工具类
 * @Author zhaoRong
 * @Date 2019/3/19 12:23
 **/
public final class Md5Utils {
    /**
     * 对字符串进行MD5编码
     *
     * @param string 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encodeByMD5(String string) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(string.getBytes());
            for (byte b : bs) {
                int x = b & 255;
                //把取出的数据转成十六进制数
                String s = Integer.toHexString(x);
                if (x < 16) {
                    sb.append("0");
                }
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
