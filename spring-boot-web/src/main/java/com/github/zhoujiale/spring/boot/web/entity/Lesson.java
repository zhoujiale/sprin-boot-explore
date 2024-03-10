package com.github.zhoujiale.spring.boot.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author zhou
 * @className Lesson
 * @descrption 课程
 * @date 2022/5/31 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    /**
     * 名称
     **/
    private String name;

    /**
     * 教师名称
     **/
    private String teacherName;

    /**
     * 上课时间
     **/
    private LocalDate lessonDate;

    /**
     * 是否线上
     **/
    private Boolean online;

    /**
     * 特点
     **/
    private List<String> features;
}
