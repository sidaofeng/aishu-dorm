package com.waken.dorm.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName StringUtils
 * @Description StringUtils
 * @Author zhaoRong
 * @Date 2019/3/21 19:56
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 字符串拼接
     *
     * @param strs
     * @param separator
     * @return
     */
    public static String arrayAsString(String[] strs, String separator) {
        StringBuffer sb = new StringBuffer();
        try {
            for (String str : strs) {
                sb.append(str).append(separator);
            }
            sb = sb.delete(sb.length() - separator.length(), sb.length());
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }
}
