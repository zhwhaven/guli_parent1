package com.haven.eduservice.controller;


import com.haven.eduservice.entity.EduChapter;
import com.haven.eduservice.service.EduChapterService;
import com.haven.eduservice.vo.ChapterVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@Api(description = "大纲管理模块")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService chapterService;

    @ApiOperation("根据课程id查询章节和小节")
    @GetMapping("/selectChapterAndVideoById/{courseId}")
    public R selectChapterAndVideoById(@PathVariable String courseId){
        List<ChapterVo> chapterVoList=chapterService.selectChapterAndVideoById(courseId);
        return R.ok().data("chapterVoList",chapterVoList);
    }

    @ApiOperation("根据章节id回显数据")
    @GetMapping("/selectChapter/{chaperId}")
    public R selectChapterBd(@PathVariable String chaperId){

        EduChapter eduChapter = chapterService.getById(chaperId);

        return R.ok().data("eduChapter",eduChapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        System.out.println(eduChapter);
         chapterService.save(eduChapter);
        return R.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/deleteChapterAndVideo/{chapterId}")
    public R deleteChapterAndVideo(@PathVariable String chapterId){
        chapterService.deleteChapterAndVideo(chapterId);
        return R.ok();
    }


}

