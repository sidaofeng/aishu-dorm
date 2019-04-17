package com.waken.dorm.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.waken.dorm.common.config.OssConstantConfig;
import com.waken.dorm.common.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
@Component
public class AliyunOSSUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OssConstantConfig ossConstantConfig;

    /**
     * 上传文件
     * 
     * @return 可访问的路径
     */
    public String upLoad(File file, String folderName) {
        String endpoint = ossConstantConfig.getEndpoint();
        String accessKeyId = ossConstantConfig.getAccessKeyId();
        String accessKeySecret = ossConstantConfig.getAccessKeySecret();
        String bucketName = ossConstantConfig.getBucketName();
        String fileHost = ossConstantConfig.getFileHost();
        String accessUrl = ossConstantConfig.getAccessUrl();
        String fileName = file.getName();
        logger.info("------OSS文件上传开始--------" + fileName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try { // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            } // 设置文件路径和名称
            String string = Constant.JPG;
            String[] strings = fileName.split("\\.");
            if (Constant.ONE < strings.length) {
                string = strings[1];
            }
            String newFileName = fileHost + "/" + (folderName + "/" + dateStr + "/" + UUIDUtils.getPkUUID() + "." + string);
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, newFileName, file));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            String imgAddress = accessUrl + "/" + newFileName;
            if (result != null) {
                logger.info("------OSS文件上传成功------" + imgAddress);
                return imgAddress;
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    /**
     * 根据key删除OSS服务器上的文件
     * 
     * @param filePath
     *            需要删除的文件路径
     * @return
     */
    public void deleteFile(String filePath) {
        String endpoint = ossConstantConfig.getEndpoint();
        String accessKeyId = ossConstantConfig.getAccessKeyId();
        String accessKeySecret = ossConstantConfig.getAccessKeySecret();
        String bucketName = ossConstantConfig.getBucketName();
        logger.info("------OSS文件删除开始--------");
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, filePath);
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
    /**
     * 批量删除object
     * 
     * @param fileNames 文件名
     */
    public void deleteBatchObect(List<String> fileNames) {
        String endpoint = ossConstantConfig.getEndpoint();
        String accessKeyId = ossConstantConfig.getAccessKeyId();
        String accessKeySecret = ossConstantConfig.getAccessKeySecret();
        String bucketName = ossConstantConfig.getBucketName();
        logger.info("------OSS批量文件删除开始--------");
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除Objects
            DeleteObjectsRequest delObjects = new DeleteObjectsRequest(bucketName).withKeys(fileNames);
            ossClient.deleteObjects(delObjects);
            logger.info("------OSS批量文件删除成功--------");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();// 关闭client
        }
    }
    /**
     * 判断oss中是否存在文件
     * 
     * @param filePath
     * @return
     */
    public Boolean doesObjectExist(String filePath) {
        String endpoint = ossConstantConfig.getEndpoint();
        String accessKeyId = ossConstantConfig.getAccessKeyId();
        String accessKeySecret = ossConstantConfig.getAccessKeySecret();
        String bucketName = ossConstantConfig.getBucketName();
        logger.info("------OSS开始判断文件是否存在--------");
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Boolean result = ossClient.doesObjectExist(bucketName, filePath);
        // 关闭OSSClient
        if (ossClient != null) {
            ossClient.shutdown();
        }
        return result;
    }
}
