package com.haven.smsService.controller;

import com.haven.smsService.service.SmsService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smsservice/sms")
@Api(description = "发送验证码模块")
@CrossOrigin
public class SmsController {
    @Autowired
    SmsService smsService;
    @GetMapping("/sendSms/{mobile}")
    @ApiOperation("发送验证码")
    public R sendSms(@PathVariable String mobile){
        if(!StringUtils.isEmpty(mobile)){
            Boolean a=smsService.sendSms(mobile);
            if(a==false){
                return R.error();
            }
        }
        return R.ok();
    }
}
