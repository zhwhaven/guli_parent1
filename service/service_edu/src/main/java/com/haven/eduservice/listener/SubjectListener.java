package com.haven.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.eduservice.entity.EduSubject;
import com.haven.eduservice.service.EduSubjectService;
import com.haven.eduservice.vo.SubjectDemo;

public class SubjectListener extends AnalysisEventListener<SubjectDemo> {

    EduSubjectService eduSubjectService;
    public SubjectListener(){
    }
//    因为该listener只能被new,
    public SubjectListener( EduSubjectService eduSubjectService){
        this.eduSubjectService=eduSubjectService;
    }
    @Override
    public void invoke(SubjectDemo subjectDemo, AnalysisContext analysisContext) {
//easyexcell读取数据时，一行行读
//        查看一级学科是否重复
        String oneSubject = subjectDemo.getOneSubject();
        EduSubject eduSubject1 = exitOneSubject(oneSubject);
        System.out.println(eduSubject1);
        if(eduSubject1==null){
//            不重复即插入
            eduSubjectService.save(new EduSubject().setTitle(oneSubject).setParentId("0"));
            eduSubject1=exitOneSubject(oneSubject);
        }
////        ?
        String pid=eduSubject1.getId();
        String twoSubject = subjectDemo.getTwoSubject();
//        EduSubject eduSubject2 = exittwoSubject(twoSubject);
        EduSubject eduSubject2 = exittwoSubject(twoSubject);
        if(eduSubject2==null){
//            不重复即插入
            eduSubjectService.save(new EduSubject().setTitle(twoSubject).setParentId(pid));
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    //        查看一级学科是否重复
    public EduSubject exitOneSubject(String title){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        wrapper.eq("title",title);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    //        查看二级级学科是否重复
    public EduSubject exittwoSubject(String title){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.ne("parent_id","0");
        wrapper.eq("title",title);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
}
