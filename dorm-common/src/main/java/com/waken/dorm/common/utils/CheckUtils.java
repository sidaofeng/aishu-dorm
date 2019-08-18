package com.waken.dorm.common.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName CheckUtils
 * @Description 验证工具类
 * @Author zhaoRong
 * @Date 2019/4/05 11:23
 **/
public class CheckUtils {
    private static CheckUtils instance;

    private HashMap<String, HttpSession> map;

    private CheckUtils() {
        map = new HashMap<String, HttpSession>();
    }

    public static CheckUtils getInstance() {
        if (instance == null) {
            instance = new CheckUtils();
        }
        return instance;
    }

    public synchronized void addMobileCode(String type, String mobile, String code, HttpSession session) {
        session.setMaxInactiveInterval(3);
        session.setAttribute("code", code);
        map.put(type + mobile, session);
    }

    public synchronized HttpSession delMobileCode(String type, String mobile) {
        try {
            return map.remove(type + mobile);
        } catch (Throwable e) {
            return null;
        }


    }

    public synchronized void setMobileCode(String type, String mobile, String code, HttpSession session) {
        session.setMaxInactiveInterval(5 * 60);
        session.setAttribute("code", code);
        map.put(type + mobile, session);
    }

    public synchronized HttpSession getMobileCode(String type, String mobile) {
        try {
            return map.get(type + mobile);
        } catch (Throwable e) {
            return null;
        }
    }

    public void test(String type, String mobile, String code, HttpSession session) {
        /*
         * 下面这种如果和hashmap搭配,要重写键的属性
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    map.remove(type+mobile);
                    // 关闭定时
                    timer.cancel();
                }
            }, 5 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    }

    /**
     * 验证手机号合法性
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneLegality(String phone) {
        String regExp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.find();
    }

    /**
     * 验证邮箱合法性
     *
     * @param email
     * @return
     */
    public static boolean isEmailLegality(String email) {
        String regExp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(email);
        return m.find();
    }
}
