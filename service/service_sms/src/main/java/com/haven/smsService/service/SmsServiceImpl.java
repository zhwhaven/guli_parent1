package com.haven.smsService.service;

import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.smsService.util.RandomUtil;
import com.haven.smsService.util.SMSUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService{
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public Boolean sendSms(String mobile) {
//        查询redis中，今天的验证码数是否超过三次，验证是否有缓存数据

        String phoneCode= redisTemplate.opsForValue().get(mobile);
        if(!StringUtils.isEmpty(phoneCode)){
           return true;
        }
        String count = redisTemplate.opsForValue().get("count" + mobile);
        if(count!=null){
            Integer codeCount = Integer.valueOf(count);
            if(codeCount>=3){
                throw new GuliException(20001,"今日申请已达上限");
            }else {
                //        获取验证码
                String random = RandomUtil.getSixBitRandom();
//
                try {
                    Boolean aBoolean = SMSUtils.sendShortMessage(mobile, random);
                    if(aBoolean){
                        //        将数据存到redis中
                        redisTemplate.opsForValue().set(mobile,random,5, TimeUnit.MINUTES);
                    }else {
                        throw new GuliException(20001,"发送验证码失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                redisTemplate.opsForValue().increment("count"+mobile);
            }
        }else{
            //        获取验证码
            String random = RandomUtil.getSixBitRandom();
//
            try {
                Boolean aBoolean = SMSUtils.sendShortMessage(mobile, random);
                if(aBoolean){
                    //        将数据存到redis中
                    redisTemplate.opsForValue().set(mobile,random,5, TimeUnit.MINUTES);
                }else {
                    throw new GuliException(20001,"发送验证码失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().set("count"+mobile,"1",12,TimeUnit.HOURS);
        }

//        记录一天内的登录次数
        return true;
    }
}
