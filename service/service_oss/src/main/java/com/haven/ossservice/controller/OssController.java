package com.haven.ossservice.controller;

import com.haven.ossservice.service.OssServic;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileupload")
@Api(description = "文件管理")
@CrossOrigin
public class OssController {
    @Autowired
    OssServic ossServic;
    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        String url=ossServic.upload(file);
        return R.ok().data("url",url);
    }
}
