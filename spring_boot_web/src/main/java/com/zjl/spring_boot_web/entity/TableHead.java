package com.zjl.spring_boot_web.entity;

import lombok.Data;

/**
 * @author zhou
 * @className TableHead
 * @descrption 表头
 * @date 2022/5/31 17:00
 */
@Data
public class TableHead {

    /**
     * 课程名称
     **/
    private String lessonName;
    /**
     * 教师名称
     **/
    private String teacherName;

    /**
     * 是否线上
     **/
    private String online;

    /**
     * 开课时间
     **/
    private String lessonDate;

    /**
     * 特点
     **/
    private String feature;
}
