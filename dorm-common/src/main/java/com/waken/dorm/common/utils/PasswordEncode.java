package com.waken.dorm.common.utils;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * @ClassName PasswordEncode
 * @Description PasswordEncode
 * @Author zhaoRong
 * @Date 2019/3/21 19:56
 **/
public class PasswordEncode {
    private static String algorithmName = "md5";
    private static int hashIterations = 4;

    public static String shiroEncode(String userName, String password) {
        userName = userName.toLowerCase();
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(userName), hashIterations).toHex();
        return newPassword;
    }

    public static String shiroEncode(String password) {
        return new SimpleHash(algorithmName, password, ByteSource.Util.bytes(password), hashIterations).toHex();
    }

    //MD5单项加密
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(PasswordEncode.shiroEncode("aishu", "aishu"));
    }
}
