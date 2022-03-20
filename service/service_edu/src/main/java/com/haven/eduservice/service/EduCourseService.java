package com.haven.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.eduservice.vo.CourseConfirm;
import com.haven.eduservice.vo.CourseForm;
import com.haven.eduservice.vo.SelectCourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(CourseForm courseForm);

    CourseForm selectCourseById(String id);

    void updateCourse(CourseForm courseForm);

    CourseConfirm showConfirm(String courseId);

    IPage<CourseConfirm> selectCoursePage(Page<CourseConfirm> courseConfirmPage, SelectCourseVo courseVo);
}
