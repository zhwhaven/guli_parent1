package com.haven.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.eduservice.entity.EduSubject;
import com.haven.eduservice.entity.EduTeacher;
import com.haven.eduservice.listener.SubjectListener;
import com.haven.eduservice.mapper.EduSubjectMapper;
import com.haven.eduservice.mapper.EduTeacherMapper;
import com.haven.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.eduservice.vo.OneSubjectVo;
import com.haven.eduservice.vo.SubjectDemo;
import com.haven.eduservice.vo.TwoSubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    EduSubjectMapper mapper;
    @Override
    public void upload(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectDemo.class,new SubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20001,"添加课程分类失败");
        }
    }

    @Override
    public List<OneSubjectVo> selectAll() {

        List<OneSubjectVo> allSubject=new ArrayList<>();
//        查询所有一级科目
        QueryWrapper<EduSubject> wrapper1=new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> eduSubjects = mapper.selectList(wrapper1);

        for (EduSubject eduSubject : eduSubjects) {
            OneSubjectVo oneSubjectVo=new OneSubjectVo();
            BeanUtils.copyProperties(eduSubject,oneSubjectVo);
            System.out.println(oneSubjectVo);
            String pid = oneSubjectVo.getId();
//            查询与一级目录关联的二级目录
            QueryWrapper<EduSubject> wrapper2=new QueryWrapper<>();
            wrapper2.eq("parent_id",pid);
            List<TwoSubjectVo> twoVoList=new ArrayList<>();
            List<EduSubject> twosubjects = mapper.selectList(wrapper2);

            for (EduSubject twosubject : twosubjects) {
                TwoSubjectVo twoSubjectVo=new TwoSubjectVo();
                BeanUtils.copyProperties(twosubject,twoSubjectVo);
                twoVoList.add(twoSubjectVo);
            }
            oneSubjectVo.setChildren(twoVoList);
            allSubject.add(oneSubjectVo);
        }

        return allSubject;
    }
}
