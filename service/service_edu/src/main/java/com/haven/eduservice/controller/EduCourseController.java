package com.haven.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.eduservice.entity.EduCourse;
import com.haven.eduservice.service.EduCourseService;
import com.haven.eduservice.vo.CourseConfirm;
import com.haven.eduservice.vo.CourseForm;
import com.haven.eduservice.vo.SelectCourseVo;
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
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@Api(description = "课程模块")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;
    @ApiOperation("添加课程")
    @PostMapping("/save")
    public R saveCourse(@RequestBody CourseForm courseForm){
       String courseId= eduCourseService.saveCourse(courseForm);
        return R.ok().data("courseId",courseId);
    }

    @ApiOperation("根据id查找课程")
    @GetMapping("/selectById/{id}")
    public R selectCourseById(@PathVariable String id){
       CourseForm courseForm=eduCourseService.selectCourseById(id);
       return R.ok().data("courseInfo",courseForm);
    }

    @ApiOperation("修改课程信息")
    @PostMapping("/update")
    public R update(@RequestBody CourseForm courseForm){
        eduCourseService.updateCourse(courseForm);
        return R.ok();
    }

    @ApiOperation("展示课程确认页面的信息")
    @GetMapping("/showConfirm/{courseId}")
    public R showConfirm(@PathVariable String  courseId){
        CourseConfirm courseConfirm=eduCourseService.showConfirm(courseId);
        return R.ok().data("courseConfirm",courseConfirm);
    }
    @ApiOperation("发布课程")
    @GetMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){
//        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
//        wrapper.eq("id",courseId);
//        wrapper.eq("status","Draft");
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation("分页查询课程")
    @GetMapping("/getPageList/{page}/{limit}")
    public R getPageList(@PathVariable int page,@PathVariable int limit,@RequestParam(value = "courseVo",required = false)SelectCourseVo courseVo){
        Page<CourseConfirm> courseConfirmPage=new Page<>(page,limit);
        eduCourseService.selectCoursePage(courseConfirmPage,courseVo);
        long total = courseConfirmPage.getTotal();
        List<CourseConfirm> records = courseConfirmPage.getRecords();
        System.out.println(records);
        return R.ok().data("list",records).data("total",total);
    }
}

