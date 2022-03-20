package com.haven.ossservice.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.haven.baseService.ExceptionHandler.GlobalExceptionHandler;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.ossservice.entity.OssEntity;
import com.haven.ossservice.service.OssServic;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
public class OssServicImpl implements OssServic {
    @Override
    public String upload(MultipartFile file) {
//            https://havenguli.oss-cn-guangzhou.aliyuncs.com/index.jpg
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。

        String endpoint = OssEntity.ENDPOINT;
        String ep = "https://" + endpoint;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = OssEntity.ACCESSKEYID;
        String accessKeySecret = OssEntity.ACCESSKEYSECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = OssEntity.BUCKETNAME;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String filename = file.getOriginalFilename();
        filename = new DateTime().toString("yyyy/MM/dd") + "/" + UUID.randomUUID().toString() + "/" + filename;
//        String objectName = "exampledir/exampleobject.txt";
        // 填写网络流地址。

//        String url = "https://www.aliyun.com/";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ep, accessKeyId, accessKeySecret);

        try {
//            InputStream inputStream = new URL(url).openStream();
            // 创建PutObject请求。
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, filename, inputStream);
//            havenguli.oss-cn-guangzhou.aliyuncs.com

            return "https://" + bucketName + "." + endpoint + "/" + filename;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException | IOException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw new GuliException(20001, "文件上传有误");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();

            }

        }
       return null;
    }
}
