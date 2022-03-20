package com.haven.eduservice.controller;

import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testlogin")
@Api(description = "模拟登录模块")
@CrossOrigin
public class TestLoginController {
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @ApiOperation("权限")
    @GetMapping("/info")
    public R getInfo(){
        return R.ok().data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
