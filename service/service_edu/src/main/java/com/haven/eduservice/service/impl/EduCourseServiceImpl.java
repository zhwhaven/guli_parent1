package com.haven.eduservice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.eduservice.entity.EduCourse;
import com.haven.eduservice.entity.EduCourseDescription;
import com.haven.eduservice.mapper.EduCourseMapper;
import com.haven.eduservice.service.EduCourseDescriptionService;
import com.haven.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.eduservice.vo.CourseConfirm;
import com.haven.eduservice.vo.CourseForm;
import com.haven.eduservice.vo.SelectCourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService courseDescriptionService;
    @Autowired
    EduCourseMapper eduCourseMapper;
    @Override
    public String saveCourse(CourseForm courseForm) {
//插入数据到course
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseForm,eduCourse);
        int i = baseMapper.insert(eduCourse);
        if(i==0){
            throw new GuliException(20001,"添加课程失败");
        }else{
            //插入数据到course_descript
            EduCourseDescription description=new EduCourseDescription();
            description.setId(eduCourse.getId());
            description.setDescription(courseForm.getDescription());
            courseDescriptionService.save(description);
        }
        return eduCourse.getId();
    }

    @Override
    public CourseForm selectCourseById(String id) {
        CourseForm courseForm=new CourseForm();
        EduCourse eduCourse = baseMapper.selectById(id);
        System.out.println(eduCourse);
        if(eduCourse!=null){
            BeanUtils.copyProperties(eduCourse,courseForm);
            EduCourseDescription courseDescription = courseDescriptionService.getById(id);
            if(courseDescription!=null){
                String description = courseDescription.getDescription();
                courseForm.setDescription(description);
            }

        }

        return courseForm;
    }

    @Override
    public void updateCourse(CourseForm courseForm) {
        try {
            EduCourse eduCourse=new EduCourse();
            BeanUtils.copyProperties(courseForm,eduCourse);
            baseMapper.updateById(eduCourse);
            EduCourseDescription eduCourseDescription=new EduCourseDescription();
            BeanUtils.copyProperties(courseForm,eduCourseDescription);
            courseDescriptionService.updateById(eduCourseDescription);
        } catch (Exception e) {
            new GuliException(20001,"修改课程信息失败");
            e.printStackTrace();
        }
    }

    @Override
    public CourseConfirm showConfirm(String courseId) {
        CourseConfirm courseConfirm=  baseMapper.courseConfirmMessage(courseId);
        return courseConfirm;
    }

    @Override
    public IPage<CourseConfirm> selectCoursePage(Page<CourseConfirm> courseConfirmPage, SelectCourseVo courseVo) {

        IPage<CourseConfirm> list=eduCourseMapper.selectCoursePage(courseConfirmPage,courseVo);
        return list;
    }
}
