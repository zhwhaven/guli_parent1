package com.haven.vodService.entity;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AliyunKeyProperties implements InitializingBean {

    @Value("${aliyun.vod.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.vod.accessKeySecret}")
    private String accessKeySecret;

    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;
    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESSKEYID=accessKeyId;
        ACCESSKEYSECRET=accessKeySecret;
    }
}
