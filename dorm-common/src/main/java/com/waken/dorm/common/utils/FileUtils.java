package com.waken.dorm.common.utils;

import com.waken.dorm.common.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName FileUtils
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/9 22:09
 **/
@Slf4j
public class FileUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 验证传入文件的格式或者是否为空
     */
    public static void checkFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServerException("导入数据为空");
        }
        String filename = file.getOriginalFilename();
        if (!StringUtils.endsWith(filename, ".xlsx")) {
            throw new ServerException("只支持.xlsx类型文件导入");
        }
    }

    public static File multipartFileToFile(MultipartFile file) {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            log.info("FileUtils: 文件为空");
            throw new ServerException("文件为空！");
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
                return toFile;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return toFile;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
