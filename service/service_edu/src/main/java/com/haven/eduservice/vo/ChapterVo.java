package com.haven.eduservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ChapterVo implements Serializable {

    String id;

    String title;

    List<VideoVo> videoVoList;
}
