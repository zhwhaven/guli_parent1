package com.haven.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.eduservice.entity.EduTeacher;
import com.haven.eduservice.mapper.EduTeacherMapper;
import com.haven.eduservice.service.EduTeacherService;
import com.haven.utilscommon.vo.R;
import com.haven.utilscommon.vo.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Api(description = "讲师管理模块")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService;
    @ApiOperation("分页查询讲师")
    @PostMapping("/selectAll/{current}/{limit}")
    public R selectAll(@RequestBody TeacherQuery teacherQuery,
                       @PathVariable int current,
                       @PathVariable int limit){
//        @ApiModelProperty(value = "教师名称,模糊查询")
//    private String name;
//
//    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
//    private Integer level;
//
//    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
//    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
//
//    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
//    private String end;
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        if(teacherQuery.getLevel()!=null){
            queryWrapper.eq("level",teacherQuery.getLevel());
        }
        if(teacherQuery.getName()!=null){
            queryWrapper.like("name",teacherQuery.getName());
        }
        if(!Strings.isEmpty(teacherQuery.getBegin())){
            queryWrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(!Strings.isEmpty(teacherQuery.getEnd())){
            queryWrapper.le("gmt_modified",teacherQuery.getEnd());
        }
        Page<EduTeacher> page=new Page<>(current,limit);
        teacherService.page(page, queryWrapper);
        List<EduTeacher> list = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",list).data("total",total);
    }



    @ApiOperation("根据id查询讲师")
    @GetMapping("/selectById/{id}")
    public R selectById(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    @ApiOperation("添加讲师")
    @PostMapping("/add")
    public R add(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }

    }
    @ApiOperation("修改讲师byid")
    @PostMapping("/update")
    public R update(@RequestBody EduTeacher eduTeacher){
        teacherService.getById(eduTeacher.getId());
        boolean update = teacherService.updateById(eduTeacher);
        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("删除讲师byid")
    @DeleteMapping ("/delete/{id}")
    public R delete(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }
    @ApiOperation("查询所有讲师")
    @GetMapping("/selectAllTeacher")
    public R selectAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("list",list);
    }




//    @ApiOperation("修改讲师byid")
//    @PutMapping("/update/{id}")
//    public Boolean update(@PathVariable String id){
//        EduTeacher teacher = teacherService.getById(id);
//        teacher.setCareer("fsdfsfsfs");
//        boolean update = teacherService.update(teacher, null);
//
//        return update;
//    }


}

