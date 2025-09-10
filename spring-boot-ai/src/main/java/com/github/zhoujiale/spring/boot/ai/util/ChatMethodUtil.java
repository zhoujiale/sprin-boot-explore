package com.github.zhoujiale.spring.boot.ai.util;

import com.github.zhoujiale.commons.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @classname: ChatFunctionUtil
 * @author: zhou
 * @description:
 * @date: 2025/9/10 12:20
 */
@Slf4j
public class ChatMethodUtil {

    @Tool(description = "获取用户今天日期")
    public String getToday(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Tool(description = "获取用户指定日期是否有空闲")
    public boolean isFree(@ToolParam(description = "指定日期") String date){
        DayOfWeek dayOfWeek = Objects.requireNonNull(DateUtil.localDateOf(date)).getDayOfWeek();
        return switch (dayOfWeek) {
            case MONDAY -> true;
            case TUESDAY -> true;
            case WEDNESDAY -> true;
            case THURSDAY -> true;
            case FRIDAY -> true;
            case SATURDAY -> false;
            case SUNDAY -> true;
            default -> false;
        };
    }
}
