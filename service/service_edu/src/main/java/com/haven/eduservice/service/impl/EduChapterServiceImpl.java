package com.haven.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.eduservice.client.VodClient;
import com.haven.eduservice.entity.EduChapter;
import com.haven.eduservice.entity.EduVideo;
import com.haven.eduservice.mapper.EduChapterMapper;
import com.haven.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.eduservice.service.EduVideoService;
import com.haven.eduservice.vo.ChapterVo;
import com.haven.eduservice.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService videoService;
    @Autowired
    VodClient vodClient;
    @Override
    public List<ChapterVo> selectChapterAndVideoById(String courseId) {
        List<ChapterVo> chapterVoList=new ArrayList<>();

        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(wrapper);

        for (EduChapter chapter : chapters) {
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            QueryWrapper<EduVideo> videoWrapper=new QueryWrapper<>();
             videoWrapper.eq("chapter_id", chapter.getId());
            List<EduVideo> videos = videoService.list(videoWrapper);
            List<VideoVo> videoVoList=new ArrayList<>();
            for (EduVideo video : videos) {
                VideoVo videoVo=new VideoVo();
                BeanUtils.copyProperties(video,videoVo);
                videoVoList.add(videoVo);
            }
            chapterVo.setVideoVoList(videoVoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    @Override
    public void deleteChapterAndVideo(String chapterId) {
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("chapter_id", chapterId);
        wrapper1.select("id","video_source_id");
        List<EduVideo> videoList = videoService.list(wrapper1);
        List<String> videoIdList=new ArrayList<>();
        if(videoList!=null){
            for (EduVideo eduVideo : videoList) {
                String videoId = eduVideo.getVideoSourceId();
                if(videoId!=null){
                    videoIdList.add(videoId);
                }
                videoService.removeById(eduVideo.getId());
            }
        }
        if(videoIdList!=null){
            vodClient.deleteVideoList(videoIdList);
        }
        baseMapper.deleteById(chapterId);
    }
}
