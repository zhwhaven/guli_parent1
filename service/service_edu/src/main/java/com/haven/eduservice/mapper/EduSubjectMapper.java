package com.haven.eduservice.mapper;

import com.haven.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-12
 */
@Mapper
@Component
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

}
