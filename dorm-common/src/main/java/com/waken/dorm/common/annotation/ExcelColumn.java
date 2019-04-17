package com.waken.dorm.common.annotation;

import com.waken.dorm.common.utils.excel.ExcelColumnFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zhaoRong
 * @Date 2019/3/30 14:02
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface ExcelColumn {
    
    /**
     * 导出到Excel中的名字.
     */
    public abstract String name();
    
    /**
     * 配置列的名称,对应A,B,C,D....
     */
    public abstract String column();
    
    /**
     * 合并方式，0：不合并、1：相邻行数据相同合并
     */
    public abstract int mergeType() default 0;
    
    /**
     * 提示信息
     */
    public abstract String prompt() default "";
    
    /**
     * 设置只能选择不能输入的列内容.
     */
    public abstract String[] combo() default {};
    
    /**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    public abstract boolean isExport() default true;

    /**
     * 统计方式，0：不统计、1：统计
     */
    public abstract int totalType() default 0;
    /**
     * 格式化类
     */
    public abstract Class<? extends ExcelColumnFormat> format() default ExcelColumnFormat.None.class;
    
}
