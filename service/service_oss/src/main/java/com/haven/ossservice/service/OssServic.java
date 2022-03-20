package com.haven.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssServic {
    String upload(MultipartFile file);
}
