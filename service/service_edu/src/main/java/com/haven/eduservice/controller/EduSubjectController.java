package com.haven.eduservice.controller;


import com.haven.eduservice.service.EduSubjectService;
import com.haven.eduservice.vo.OneSubjectVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@Api(description = "课程分类模块")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;
    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        eduSubjectService.upload(file,eduSubjectService);
        return R.ok();
    }

    @ApiOperation("展示课程")
    @GetMapping("/selectAll")
    public R selectAll(){
    List<OneSubjectVo> allSubject= eduSubjectService.selectAll();
        return R.ok().data("allSubject",allSubject);
    }

}

