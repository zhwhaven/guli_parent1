package com.haven.eduservice.client.Impl;

import com.haven.eduservice.client.VodClient;
import com.haven.utilscommon.vo.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public R uploadVideo(MultipartFile file) {
        return R.error();
    }

    @Override
    public R getVideoPlayInfo(String videoId) {
        return R.error();
    }

    @Override
    public R deleteVideo(String videoId) {
        return R.error();
    }

    @Override
    public R deleteVideoList(List<String> videoList) {
        return R.error();
    }
}
