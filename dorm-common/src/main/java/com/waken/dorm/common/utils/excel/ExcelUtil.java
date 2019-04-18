package com.waken.dorm.common.utils.excel;

import com.waken.dorm.common.annotation.ExcelColumn;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.utils.DateUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ExcelUtil
 * @Description ExcelUtil
 * @Author zhaoRong
 * @Date 2019/3/30 14:02
 **/
public class ExcelUtil<T> {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    Class<T> clazz;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }
    public static boolean isExcel(String fileName){
        if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
            return true;

        }else {
            logger.info("文件不是excel类型");
            return false;

        }
    }
    /**
     * 
     * @Title: importExcel
     * @Description: 导入excel
     * @param multipartFile
     * @param startRow
     * @return
     * @return: List<T>
     */
    public List<T> importExcel(MultipartFile multipartFile, int startRow) {
        String sheetName = multipartFile.getOriginalFilename();
        logger.info("开始解析excel :" +sheetName);
        if (!ExcelUtil.isExcel(sheetName)){
            throw new DormException("文件不是excel类型");
        }

        FileInputStream input = null;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            input = (FileInputStream)inputStream;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("转换输入流失败，原因："+e.getMessage());
        }
        Workbook workbook = null;
        Sheet sheet = null;
        // 总列数
        int maxCol = 0;
        startRow = startRow < 0 ? 1 : startRow;
        List<T> list = new ArrayList<T>();
        try {
            workbook = WorkbookFactory.create(input);

            if (null != sheetName && !sheetName.trim().equals("")) {
                // 如果指定sheet名,则取指定sheet中的内容.
                sheet = workbook.getSheet(sheetName);
            }
            if (sheet == null) {
                // 如果传入的sheet名不存在则默认指向第1个sheet.
                sheet = workbook.getSheetAt(0);
            }
            
            int rows = sheet.getPhysicalNumberOfRows();
            
            if (rows > 0) {
                // 有数据时才处理
                List<Field> allFields = getMappedFiled(clazz, null);
                // 定义一个map用于存放列的序号和field.
                Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
                for (Field field : allFields) {
                    ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
                    int col = getExcelCol(attr.column());// 获得列号
                    maxCol = Math.max(col, maxCol);
                    field.setAccessible(true);// 设置类的私有字段属性可访问.
                    fieldsMap.put(col, field);
                }
                // 从第2行开始取数据,默认第一行是表头.
                for (int i = startRow; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    T entity = null;
                    for (int j = 0; j <= maxCol; j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        String c = "";
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                c = cell.getRichStringCellValue().getString();
                            break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    c = sdf.format(cell.getDateCellValue());
                                } else {
                                    c = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                c = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                break;
                            case BLANK:
                                break;
                            default:
                        }
                        
                        if (c == null || c.equals("")) {
                            continue;
                        }
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        if (field == null) {
                            continue;
                        } else {
                            ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
                            if (attr.format() != ExcelColumnFormat.None.class) {
                                try {
                                    ExcelColumnFormat format = attr.format().newInstance();
                                    c = format.parse(c);
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                }
                            }
                            
                        }
                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            field.set(entity, c);
                        } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                            field.set(entity, new BigDecimal(c).intValue());
                        } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                           Long val =  Math.round(new Double(c));
                            field.set(entity, val);
                        } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(c));
                        } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(c));
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        } else if (Date.class == fieldType) {
                             try {
                                field.set(entity, sdf.parse(c));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                        }
                        
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e1) {
            e1.printStackTrace();
        } catch (InvalidFormatException e1) {
            e1.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    /**
     * 导出模板Excel文件
     * @param response
     * @return
     */
    public void downTemplate(HttpServletResponse response, String templetName, String fileName) {
        InputStream instream = null;
        String templatePath = "";
        String urlPath = this.getClass().getClassLoader().getResource("").getPath().toString();
        System.out.println("=项目所在路径：" + urlPath);
        String fileSeparator = System.getProperty("file.separator");
        System.out.println("=文件分隔符：" + fileSeparator);
        templatePath = ""+urlPath+"/template/"+templetName;  
        if(fileSeparator.equals("\\")){
            templatePath = templatePath.replace("/", "\\");
        }else if (fileSeparator.equals("/")){
            templatePath = templatePath.replace("/", "/");
        }
        System.out.println("=最终路径：" + templatePath);

        try {
            instream = new FileInputStream( new File(templatePath));
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName+".xls", "UTF-8")+"");
            byte[] b = new byte[100];
            int len;
            while((len = instream.read(b))>0)
                response.getOutputStream().write(b,0,len);
            instream.close();
        } catch (Exception e) {
            try {
                if(instream != null){
                    instream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                if(instream != null){
                    instream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    
    /**
     * 
     * @Title: exportExcel
     * @Description: 将List数据导出到输出流，数据数必须与页签数保持一致
     * @param lists
     * @param sheetNames
     * @param output
     * @return
     * @return: boolean
     */
    public boolean exportExcel(List<T> lists[], String sheetNames[], OutputStream output) {
        if (lists.length != sheetNames.length) {
            // 数据数和页签数不一致
            return false;
        }
        Workbook workbook = new XSSFWorkbook();
        List<Field> fields = getMappedFiled(clazz, null);
        Sheet sheet = null;
        List<T> list = null;
        for (int dataIndex = 0; dataIndex < lists.length; dataIndex++) {
            sheet = workbook.createSheet();
            workbook.setSheetName(dataIndex, sheetNames[dataIndex]);
            list = lists[dataIndex];
            // 创建表头
            createSheetHeader(fields, sheet);
            if(list.size()>0)//创建统计行
            {
                Row totalRow=sheet.createRow(list.size() + 1);
                ExcelColumn attr = null;
                Cell cell = null;
                Field field = null;
                for (int j = 0; j < fields.size(); j++) {
                    field = fields.get(j);// 获得field.
                    field.setAccessible(true);// 设置实体类私有属性可访问
                    attr = field.getAnnotation(ExcelColumn.class);
                    if(attr.totalType()==1&&attr.format() != ExcelColumnFormat.None.class)
                    {
                        cell = totalRow.createCell(getExcelCol(attr.column()));// 创建cell
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue("0");
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                // 创建行记录
                createSheetRowWithTotal(fields, sheet.createRow(i + 1), (T) list.get(i),sheet,list.size() + 1);
            }
        }
        try {
            output.flush();
            workbook.write(output);
            output.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    /**
     * 
     * @Title: createSheetRow
     * @Description: 创建一行记录
     * @param fields
     * @param row
     * @param vo
     * @return: void
     */
    private void createSheetRow(List<Field> fields, Row row, T vo) {
        Cell cell = null;
        Field field = null;
        ExcelColumn attr = null;
        for (int j = 0; j < fields.size(); j++) {
            field = fields.get(j);// 获得field.
            field.setAccessible(true);// 设置实体类私有属性可访问
            attr = field.getAnnotation(ExcelColumn.class);
            try {
                String cellValue = "";
                // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                if (attr.isExport()) {
                    cell = row.createCell(getExcelCol(attr.column()));// 创建cell
                    cell.setCellType(CellType.STRING);
                    Object obj = field.get(vo);
                    if (obj == null) {
                        cell.setCellValue("");
                    } else {
                        Class<?> fieldType = field.getType();
                        if (Date.class == fieldType) {
                            try {
                                cellValue = sdf.format((Date) obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            cellValue = String.valueOf(obj);
                        }
                        if (attr.format() != ExcelColumnFormat.None.class) {
                            try {
                                ExcelColumnFormat format = attr.format().newInstance();
                                cell.setCellValue(format.print(cellValue));
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        } else {
                            cell.setCellValue(cellValue);
                        }
                    }
                }
                // 0、不合并；1、相邻行数据相同合并
                if (attr.mergeType() == 1) {
                    boolean toMege = true;
                    // 上一个单元格
                    Cell cAbove = row.getSheet().getRow(row.getRowNum() - 1).getCell(getExcelCol(attr.column()));
                    if (!cellValue.equals(cAbove.getStringCellValue())) {
                        toMege = false;
                    } else {
                        // 前一个单元格
                        Cell cBefore1 = null;
                        Cell cBefore2 = null;
                        for (int iii = getExcelCol(attr.column()) - 1; iii >= 0; iii--) {
                            cBefore1 = row.getSheet().getRow(row.getRowNum()).getCell(iii);
                            cBefore2 = row.getSheet().getRow(row.getRowNum() - 1).getCell(iii);
                            if (!cBefore1.getStringCellValue().equals(cBefore2.getStringCellValue())) {
                                toMege = false;
                                break;
                            }
                        }
                    }
                    if (toMege) {
                        List<CellRangeAddress> regions = row.getSheet().getMergedRegions();
                        boolean flag = true;
                        for (int ii = 0; ii < regions.size(); ii++) {
                            CellRangeAddress range = regions.get(ii);
                            if (range.containsColumn(cAbove.getColumnIndex())
                                    && range.containsRow(cAbove.getRowIndex())) {
                                row.getSheet().removeMergedRegion(ii);
                                range.setLastRow(cell.getRowIndex());
                                range.setLastColumn(cell.getColumnIndex());
                                row.getSheet().addMergedRegion(range);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            CellRangeAddress range = new CellRangeAddress(cAbove.getRowIndex(), cell.getRowIndex(),
                                    cAbove.getColumnIndex(), cell.getColumnIndex());
                            row.getSheet().addMergedRegion(range);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    /**
     * 
     * @Title: createSheetHeader
     * @Description: 创建表头
     * @param
     * @param fields
     * @param sheet
     * @return: void
     */
    private void createSheetHeader(List<Field> fields, Sheet sheet) {
        Row row = sheet.createRow(0);
        row.setHeight((short) 440);
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setColor(IndexedColors.GREY_80_PERCENT.index);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(CellUtil.FILL_FOREGROUND_COLOR, IndexedColors.GREY_25_PERCENT.index);
        properties.put(CellUtil.FILL_PATTERN, FillPatternType.SOLID_FOREGROUND);
        
        // 写入各个字段的列头名称
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
            // 获得列号
            int col = getExcelCol(attr.column());
            // 创建列
            Cell cell = row.createCell(col);
            // 设置列中写入内容为String类型
            cell.setCellType(CellType.STRING);
            // 写入列名
            cell.setCellValue(attr.name());
            // 设置列宽度
            sheet.setColumnWidth(i, attr.name().getBytes().length * 256 * 2 + 1500);
            
            // 如果设置了提示信息则鼠标放上去提示.
            if (!attr.prompt().trim().equals("")) {
                setPrompt(sheet, "", attr.prompt(), 1, 100, col, col);// 这里默认设了2-101列提示.
            }
            // 如果设置了combo属性则本列只能选择不能输入
            if (attr.combo().length > 0) {
                setValidation(sheet, attr.combo(), 1, 100, col, col);// 这里默认设了2-101列只能选择不能输入.
            }
            CellUtil.setCellStyleProperties(cell, properties);
            CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
            CellUtil.setFont(cell, font);
        }
    }
    
    /**
     * 
     * @Title: exportExcel
     * @Description: 将List数据导出到输出流，数据数必须与页签数保持一致
     * @param list
     * @param sheetName
     * @param output
     * @return
     * @return: boolean
     */
    public boolean exportExcel(List<T> list, String sheetName, OutputStream output) {
        List<T>[] lists = new ArrayList[1];
        lists[0] = list;
        String[] sheetNames = new String[1];
        sheetNames[0] = sheetName;
        
        return exportExcel(lists, sheetNames, output);
    }
    
    /**
     * 
     * @Title: getExcelCol
     * @Description: 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     * @param col
     * @return
     * @return: int
     */
    private static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }
    
    /**
     * 
     * @Title: setPrompt
     * @Description: 设置单元格上提示
     * @param sheet
     * @param promptTitle
     * @param promptContent
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     * @return: Sheet
     */
    private static Sheet setPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
            int firstCol, int endCol) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        // 构造constraint对象
        XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint) dvHelper.createCustomConstraint("DD1");
        
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        XSSFDataValidation data_validation_view = (XSSFDataValidation) dvHelper.createValidation(constraint, regions);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        data_validation_view.setShowPromptBox(true);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }
    
    /**
     * 
     * @Title: setValidation
     * @Description: 设置某些列的值只能输入预制的数据,显示下拉框.
     * @param sheet
     * @param textlist
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     * @return: Sheet
     */
    private static Sheet setValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol,
            int endCol) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        // 加载下拉列表内容
        XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(constraint, regions);
        data_validation_list.setShowErrorBox(true);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }
    
    /**
     * 
     * @Title: getMappedFiled
     * @Description: 获取注解的属性包含所有父类中的属性
     * @param clazz
     * @param fields
     * @return
     * @return: List<Field>
     */
    private List<Field> getMappedFiled(Class<?> clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }
        // 得到所有定义的属性
        Field[] allFields = clazz.getDeclaredFields();
        // 获取ExcelColumn 注解的field
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                fields.add(field);
            }
        }
        // 获取父类中注解的属性
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields);
        }
        return fields;
    }

    /**
     *
     * @Title: createSheetRow
     * @Description: 创建一行记录合并数据值
     * @param fields
     * @param row
     * @param vo
     * @return: void
     */
    private void createSheetRowWithTotal(List<Field> fields, Row row, T vo,Sheet sheet,int totalNum) {
        Cell cell = null;
        Field field = null;
        ExcelColumn attr = null;
        for (int j = 0; j < fields.size(); j++) {
            field = fields.get(j);// 获得field.
            field.setAccessible(true);// 设置实体类私有属性可访问
            attr = field.getAnnotation(ExcelColumn.class);
            try {
                String cellValue = "";
                // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                if (attr.isExport()) {
                    cell = row.createCell(getExcelCol(attr.column()));// 创建cell
                    cell.setCellType(CellType.STRING);
                    Object obj = field.get(vo);
                    if (obj == null) {
                        cell.setCellValue("");
                    } else {
                        Class<?> fieldType = field.getType();
                        if (Date.class == fieldType) {
                            try {
                                cellValue = sdf.format((Date) obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            cellValue = String.valueOf(obj);
                        }
                        if (attr.format() != ExcelColumnFormat.None.class) {
                            try {
                                ExcelColumnFormat format = attr.format().newInstance();
                                if(attr.totalType()==1)
                                {
                                    Row totalRow=sheet.getRow(totalNum);
                                    Cell cellToal =totalRow.getCell(getExcelCol(attr.column()));// 得到cell


                                    Double cellValueTotal=Double.parseDouble(cellToal.getStringCellValue()) ;
                                    cellValueTotal=cellValueTotal+Double.parseDouble(format.print(cellValue));
                                    DecimalFormat decfmt = new DecimalFormat("##0.00");
                                    cellToal.setCellType(CellType.STRING);
                                    cellToal.setCellValue( decfmt.format(cellValueTotal));
                                }
                                cell.setCellValue(format.print(cellValue));
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        } else {
                            cell.setCellValue(cellValue);
                        }
                    }
                }
                // 0、不合并；1、相邻行数据相同合并
                if (attr.mergeType() == 1) {
                    boolean toMege = true;
                    // 上一个单元格
                    Cell cAbove = row.getSheet().getRow(row.getRowNum() - 1).getCell(getExcelCol(attr.column()));
                    if (!cellValue.equals(cAbove.getStringCellValue())) {
                        toMege = false;
                    } else {
                        // 前一个单元格
                        Cell cBefore1 = null;
                        Cell cBefore2 = null;
                        for (int iii = getExcelCol(attr.column()) - 1; iii >= 0; iii--) {
                            cBefore1 = row.getSheet().getRow(row.getRowNum()).getCell(iii);
                            cBefore2 = row.getSheet().getRow(row.getRowNum() - 1).getCell(iii);
                            if (!cBefore1.getStringCellValue().equals(cBefore2.getStringCellValue())) {
                                toMege = false;
                                break;
                            }
                        }
                    }
                    if (toMege) {
                        List<CellRangeAddress> regions = row.getSheet().getMergedRegions();
                        boolean flag = true;
                        for (int ii = 0; ii < regions.size(); ii++) {
                            CellRangeAddress range = regions.get(ii);
                            if (range.containsColumn(cAbove.getColumnIndex())
                                    && range.containsRow(cAbove.getRowIndex())) {
                                row.getSheet().removeMergedRegion(ii);
                                range.setLastRow(cell.getRowIndex());
                                range.setLastColumn(cell.getColumnIndex());
                                row.getSheet().addMergedRegion(range);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            CellRangeAddress range = new CellRangeAddress(cAbove.getRowIndex(), cell.getRowIndex(),
                                    cAbove.getColumnIndex(), cell.getColumnIndex());
                            row.getSheet().addMergedRegion(range);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}