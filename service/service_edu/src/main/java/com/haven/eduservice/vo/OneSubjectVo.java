package com.haven.eduservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class OneSubjectVo {
    private String id;
    private String title;
    private List<TwoSubjectVo> children;
}
