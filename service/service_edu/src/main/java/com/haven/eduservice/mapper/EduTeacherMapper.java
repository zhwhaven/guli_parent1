package com.haven.eduservice.mapper;

import com.haven.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-05
 */
@Repository
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

}
