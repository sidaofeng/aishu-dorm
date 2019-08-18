package com.waken.dorm.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName DateUtils
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/21 19:56
 **/
public class DateUtils {
    /**
     * 所有地方可用
     */
    public final static String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYY_MM_DD_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * Activiti流程中专用
     */
    public final static String YYYY_MM_DD_T_HH_mm_ss = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 所有地方可用
     */
    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String CST = "EEE MMM dd HH:mm:ss 'CST' yyyy";

    public final static String YYYY_MM_DD_ZN_CH = "yyyy年MM月dd日";

    public final static String HH_mm = "HH:mm";
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * The Constant LOGGER.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static final long ONE_HOUR = 60 * 60 * 1000;

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * Gets the server time.
     *
     * @return the server time
     */
    public static long getServerTime() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss.
     *
     * @param date the date
     * @return the string
     */
    public static String format(Date date) {
        return format(date, DateUtils.YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 格式化显示当前日期.
     *
     * @param format the format
     * @return the string
     */
    public static String format(String format) {
        return format(new Date(), format);
    }

    /**
     * 日期格式化.
     *
     * @param date   the date
     * @param format the format
     * @return the string
     */
    public static String format(Date date, String format) {
        if (date == null)
            return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 时间格式化， 传入毫秒.
     *
     * @param time the time
     * @return the string
     */
    public static String dateFormat(long time) {
        return format(new Date(time), DateUtils.YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 判断时间是否超时
     *
     * @param time
     * @param intervalHour
     * @return
     */
    public static boolean isCurrentTimeOut(long time, int intervalHour) {
        Long hour = (System.currentTimeMillis() - time) / ONE_HOUR;
        return hour > intervalHour;
    }

    /**
     * 日期字符串从旧格式转换为新格式.
     *
     * @param dateStr   the date str
     * @param oldFromat the old fromat
     * @param newFormat the new format
     * @return the string
     */
    public static String format(String dateStr, String oldFromat, String newFormat) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }

            SimpleDateFormat sdf1 = new SimpleDateFormat(oldFromat);
            Date date = sdf1.parse(dateStr);

            return format(date, newFormat);

        } catch (Exception e) {
            LOGGER.warn("日期格式化转换失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 日期字符串转换为Calendar对象.
     *
     * @param dateStr       the date str
     * @param dateStrFormat the date str format
     * @return the calendar
     */
    public static Calendar format(String dateStr, String dateStrFormat) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(dateStrFormat);
            Date date = sdf.parse(dateStr);
            Calendar ca = GregorianCalendar.getInstance();
            ca.setTime(date);
            return ca;
        } catch (Exception e) {
            LOGGER.warn("日期格式化转换失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 日期字符串转换为Calendar对象.
     *
     * @param date the date str
     *             the date str format
     * @return the calendar
     */
    public static Calendar formatCalendar(Date date) {
        try {
            if (date == null) {
                return null;
            }
            Calendar ca = GregorianCalendar.getInstance();
            ca.setTime(date);
            return ca;
        } catch (Exception e) {
            LOGGER.warn("日期格式化转换失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 日期字符串转换为Date对象.
     *
     * @param dateStr       the date str
     * @param dateStrFormat the date str format
     * @return the date
     */
    public static Date dateStr2Date(String dateStr, String dateStrFormat) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(dateStrFormat);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            LOGGER.warn("日期格式化转换失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 增加日期的天数.
     *
     * @param date   the date
     * @param amount the amount
     * @return the date
     * @createTime 2014-5-22 下午11:49:56
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Adds the hours.
     *
     * @param date   the date
     * @param amount the amount
     * @return the date
     */
    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Adds the seconds.
     *
     * @param date   the date
     * @param amount the amount
     * @return the date
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * Adds the.
     *
     * @param date   the date
     * @param field  the field
     * @param amount the amount
     * @return the date
     */
    private static Date add(Date date, int field, int amount) {

        if (null == date) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();

    }

    /**
     * 格式化日期字符串为日期时间字符串，以0点0时0分开始。例如：2012-04-14 格式化为2012-04-14 00:00:00
     *
     * @param dateStr the date str
     * @return the string
     */
    public static String formatDate2DateTimeStart(String dateStr) {
        Calendar calendar = DateUtils.format(dateStr, DateUtils.YYYY_MM_DD);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return format(calendar.getTime());
    }

    /**
     * 格式化日期字符串为日期时间字符串，以23点59时59分结束。例如：2012-04-14 格式化为2012-04-14 23:59:59
     *
     * @param dateStr the date str
     * @return the string
     */
    public static String formatDate2DateTimeEnd(String dateStr) {
        Calendar calendar = DateUtils.format(dateStr, DateUtils.YYYY_MM_DD);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return format(calendar.getTime());
    }

    /**
     * 格式化日期字符串为日期时间字符串，以0点0时0分开始。例如：2012-04-14 格式化为2012-04-14 00:00:00
     *
     * @param date the date str
     * @return the string
     */
    public static Date formatDate2DateTimeStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 格式化日期字符串为日期时间字符串，以23点59时59分结束。例如：2012-04-14 格式化为2012-04-14 23:59:59
     *
     * @param date param date str
     * @return the string
     */
    public static Date formatDate2DateTimeEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 日期格式化 返回格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date formatDateStr2Date(String date) {
        return DateUtils.dateStr2Date(date, DateUtils.YYYY_MM_DD_HH_mm_ss);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getTodayStr() {
        return format(new Date(), YYYY_MM_DD);
    }

    public static Date getTodayMinTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getTodayMaxTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    public static String getWeek(Date date) {
        Map<String, Integer> weekMap = new HashMap<String, Integer>();
        weekMap.put("星期日", 0);
        weekMap.put("星期一", 1);
        weekMap.put("星期二", 2);
        weekMap.put("星期三", 3);
        weekMap.put("星期四", 4);
        weekMap.put("星期五", 5);
        weekMap.put("星期六", 6);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINA);
        String week = sdf.format(date);
        LOGGER.info("week value:" + week + "    " + weekMap.get(week));
        return weekMap.get(week).toString();
    }

    public static int getWeeks(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 获取日期时间字符
     *
     * @param date
     * @param dateTimePattern
     * @return
     */
    public static final String getDateTimeAsString(Date date, String dateTimePattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(dateTimePattern);
            return df.format(date);
        } else {
            return null;
        }
    }

    /**
     * 获取当前日期时间字符
     *
     * @param dateTimePattern
     * @return
     */
    public static final String getCurrentDateTimeAsString(String dateTimePattern) {
        return getDateTimeAsString(new Date(), dateTimePattern);
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getDateTimeAsString(new Date(), HH_mm));

    }
}
