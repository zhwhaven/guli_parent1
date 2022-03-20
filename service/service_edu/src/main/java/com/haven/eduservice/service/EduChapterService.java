package com.haven.eduservice.service;

import com.haven.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.eduservice.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> selectChapterAndVideoById(String courseId);

    void deleteChapterAndVideo(String chapterId);
}
