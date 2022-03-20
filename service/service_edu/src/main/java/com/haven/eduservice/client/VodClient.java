package com.haven.eduservice.client;

import com.haven.eduservice.client.Impl.VodClientImpl;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Component
@FeignClient(name= "service-vod",fallback = VodClientImpl.class)
public interface VodClient {
    @ApiOperation("上传视频")
    @PostMapping("/eduvod/vod-service/uploadVideo")
    public R uploadVideo(@RequestBody MultipartFile file);

    @ApiOperation("获取播放凭证")
    @GetMapping("/eduvod/vod-service/getVideoPlayInfo/{videoId}")
    public R getVideoPlayInfo(@PathVariable String videoId);

    @ApiOperation("删除单个视频")
    @DeleteMapping("/eduvod/vod-service/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId);

    @ApiOperation("删除多个视频")
    @DeleteMapping("/eduvod/vod-service/deleteVideo")
    public R deleteVideoList(@Param("videoList") List<String> videoList);
}
