package com.haven.eduservice.controller;


import com.haven.eduservice.entity.EduComment;
import com.haven.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-04
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {
   @Autowired
    EduCommentService commentService;
}

