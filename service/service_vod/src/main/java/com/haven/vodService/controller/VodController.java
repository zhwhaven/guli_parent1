package com.haven.vodService.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.haven.utilscommon.vo.R;
import com.haven.vodService.entity.AliyunKeyProperties;
import com.haven.vodService.service.VodService;
import com.haven.vodService.util.AliyunVodUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/vod-service")
@Api(description = "视频管理")
@CrossOrigin
public class VodController {
    @Autowired
    VodService vodService;
    @ApiOperation("上传视频")
    @PostMapping("/uploadVideo")
    public R uploadVideo(@RequestBody MultipartFile file){
        String videoId=vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @ApiOperation("获取播放凭证")
    @GetMapping("/getVideoPlayInfo/{videoId}")
    public R getVideoPlayInfo(@PathVariable String videoId){
        String playAuth=vodService.getVideoPlayInfo(videoId);
        return R.ok().data("PlayAuth",playAuth);

    }

    @ApiOperation("删除单个视频")
    @DeleteMapping("/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        vodService.deleteVideo(videoId);
        return R.ok();
    }

    @ApiOperation("删除多个视频")
    @DeleteMapping("/deleteVideo")
    public R deleteVideoList(@Param("videoList") List<String> videoList ){
        vodService.deleteVideoList(videoList);
        return R.ok();
    }
}
