package com.waken.dorm.common.utils;

import com.waken.dorm.common.authentication.EncryptUtil;
import com.waken.dorm.common.constant.CacheConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/8/14 22:36
 **/
@Slf4j
public class TokenUtils {
    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(CacheConstant.TOKEN_CACHE_PREFIX);
            return encryptUtil.encrypt(token);
        } catch (Exception e) {
            log.info("token加密失败：", e);
            return null;
        }
    }

    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(CacheConstant.TOKEN_CACHE_PREFIX);
            return encryptUtil.decrypt(encryptToken);
        } catch (Exception e) {
            log.info("token解密失败：", e);
            return null;
        }
    }
}
