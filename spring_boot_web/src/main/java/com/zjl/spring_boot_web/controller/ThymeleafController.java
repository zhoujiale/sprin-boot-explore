package com.zjl.spring_boot_web.controller;

import com.zjl.spring_boot_web.entity.Lesson;
import com.zjl.spring_boot_web.entity.TableHead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhou
 * @className ThymeleafController
 * @descrption 模板引擎
 * @date 2022/5/31 15:53
 */
@Slf4j
@Controller
@RequestMapping(value = "/thymeleafHtml")
public class ThymeleafController {

    /**
     * @description 列表案例
     * @date 2022/5/31 16:54
     * @author zhou
     * @param model
     * @return java.lang.String
     */
    @ModelAttribute("lessonList")
    public List<Lesson> list(Model model){
        List<Lesson> lessonList = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        lesson1.setName("语文课");
        lesson1.setTeacherName("李老师");
        lesson1.setOnline(Boolean.FALSE);
        lesson1.setLessonDate(LocalDate.of(2022,1,25));
        lesson1.setFeatures(Arrays.asList(new String[]{"耐心","业务能力强"}));
        lessonList.add(lesson1);
        Lesson lesson2 = new Lesson();
        lesson2.setName("数学课");
        lesson2.setTeacherName("王老师");
        lesson2.setOnline(Boolean.TRUE);
        lesson2.setLessonDate(LocalDate.of(2022,8,20));
        lesson2.setFeatures(Arrays.asList(new String[]{"经验丰富","获过奖"}));
        lessonList.add(lesson2);
        return lessonList;
    }

    /**
     * @ModelAttribute 可以在当前controller的url被执行前执行
     * 也就是这里个index,适用于一个controller一个url
     * 可以将页面上的data分开处理
     *
     * **/
    @ModelAttribute("tableHead")
    public TableHead tableHead(){
        TableHead tableHead = new TableHead();
        tableHead.setLessonName("课程名");
        tableHead.setTeacherName("教师名称");
        tableHead.setFeature("特点");
        tableHead.setOnline("是否线上");
        tableHead.setLessonDate("开课时间");
        return tableHead;
    }


    @GetMapping(value = "/list")
    public String index(){
        return "lessonList";
    }
}
