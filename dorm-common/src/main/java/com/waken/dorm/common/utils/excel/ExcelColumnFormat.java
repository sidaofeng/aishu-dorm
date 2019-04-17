package com.waken.dorm.common.utils.excel;

/**
 * @ClassName ExcelColumnFormat
 * @Description ExcelColumnFormat
 * @Author zhaoRong
 * @Date 2019/3/30 14:02
 **/
public abstract class ExcelColumnFormat {
    public abstract String parse(String t);
    
    public abstract String print(String t);
    
    public abstract static class None extends ExcelColumnFormat {
        
    }
    
}
