package com.haven.cmsService.api;

import com.haven.cmsService.entity.CrmBanner;
import com.haven.cmsService.service.CrmBannerService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cmsservice/api-banner")
@Api(description = "背景图片展示")
@CrossOrigin
public class BannerController {
    @Autowired
    CrmBannerService bannerService;
    @GetMapping("/selectAllBanner1")
    @ApiOperation("查询所有背景")
    public R selectAllBanner1(){
        List<CrmBanner> banners = bannerService.list(null);
        return R.ok().data("banners",banners);
    }
}
