package com.haven.eduservice.api;

import com.haven.eduservice.service.EduCourseService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/api-index")
@Api(description = "首页管理")
@CrossOrigin
public class IndexController {
    @Autowired
    EduCourseService courseService;
    @GetMapping("/getTeacherAndCourse")
    public R getTeacherAndCourse(){
        Map<String, List> map=courseService.getTeacherAndCourse();
        List teacherList = map.get("teacherList");
        List courseList = map.get("courseList");
        return R.ok().data("teacherList",teacherList).data("courseList",courseList);
    }
}
