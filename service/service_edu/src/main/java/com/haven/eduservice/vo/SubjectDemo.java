package com.haven.eduservice.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectDemo {
    @ExcelProperty(value = "一级科目",index = 0)
    private String oneSubject;
    @ExcelProperty(value = "二级科目",index = 1)
    private String twoSubject;

}
