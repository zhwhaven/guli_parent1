package com.haven.ossservice.entity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "aliyun.oss")
public class OssEntity implements InitializingBean {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    public static String ENDPOINT;
    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;
    public static String BUCKETNAME;



    @Override
    public void afterPropertiesSet() throws Exception {

        ENDPOINT=endpoint;
        ACCESSKEYID=accessKeyId;
        ACCESSKEYSECRET=accessKeySecret;
        BUCKETNAME=bucketName;
    }
}
