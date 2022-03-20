package com.haven.eduservice.controller;


import com.haven.eduservice.client.VodClient;
import com.haven.eduservice.entity.EduVideo;
import com.haven.eduservice.service.EduVideoService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@Api(description = "小节管理模块")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService videoService;
    @Autowired
    VodClient vodClient;
    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }
    @ApiOperation("查询小节")
    @GetMapping("/selectVideo/{id}")
    public R selectVideo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        return R.ok().data("eduVideo",eduVideo);
    }

    @ApiOperation("修改小节")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        EduVideo video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(videoSourceId!=null){
            vodClient.deleteVideo(videoSourceId);
        }
        videoService.removeById(id);
        return R.ok();
    }
}

