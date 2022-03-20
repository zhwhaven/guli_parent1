package com.haven.eduservice.service;

import com.haven.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.eduservice.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-12
 */
public interface EduSubjectService extends IService<EduSubject> {

    void upload(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubjectVo> selectAll();
}
