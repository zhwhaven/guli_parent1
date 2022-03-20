package com.haven.vodService.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideo(MultipartFile file);

    String getVideoPlayInfo(String videoId);

    void deleteVideo(String videoId);

    void deleteVideoList(List<String> videoList);
}
