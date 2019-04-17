package com.waken.dorm.common.utils;

import com.waken.dorm.common.exception.DormException;
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
public class FileUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static File multipartFileToFile(MultipartFile file) {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            System.out.println("FileUtils: 文件为空");
            throw new DormException("文件为空！");
        } else {
            InputStream ins = null;
            try{
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
                return toFile;
            }catch (Exception e){
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
