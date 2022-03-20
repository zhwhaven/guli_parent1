package com.haven.eduservice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haven.eduservice.vo.CourseConfirm;
import com.haven.eduservice.vo.SelectCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-14
 */
@Mapper
@Component
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseConfirm courseConfirmMessage(@PathVariable String id);

    IPage<CourseConfirm> selectCoursePage(Page<CourseConfirm> courseConfirmPage, @Param(value = "courseVo")SelectCourseVo courseVo);
}
