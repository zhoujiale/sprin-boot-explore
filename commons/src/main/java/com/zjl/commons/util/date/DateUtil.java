package com.zjl.commons.util.date;

import com.zjl.commons.util.log.ErrorLogUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @name: DateUtil
 * @description: 时间转换工具
 * @author: zhou
 * @create: 2021-02-12 12:37
 */
public class DateUtil {

    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final String DAY = "yyyy-MM-dd";

    private static final int ZONE = 8;

    public static LocalDateTime localDateTimeOf(String strDateTime){
        if (StringUtils.isBlank(strDateTime)){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME);
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(strDateTime, dateTimeFormatter);
        }catch (DateTimeParseException e){
            ErrorLogUtil.errorLog(e);
            return null;
        }
        return localDateTime;
    }

    public static LocalDate localDateOf(String strDate){
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DAY);
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(strDate,dateTimeFormatter);
        }catch (DateTimeParseException e){
            ErrorLogUtil.errorLog(e);
            return null;
        }
        return localDate;
    }

    public static LocalDateTime localDateTimeOf(Long timestamp){
        if(null == timestamp){
            return null;
        }
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(ZONE));
        }catch (DateTimeParseException e){
            ErrorLogUtil.errorLog(e);
            return null;
        }
        return localDateTime;
    }

    public static LocalDate localDateOf(Long timestamp){
        if(null == timestamp){
            return null;
        }
        LocalDate localDate;
        try{
            localDate = LocalDate.ofEpochDay(timestamp);
        }catch (DateTimeParseException e){
            ErrorLogUtil.errorLog(e);
            return null;
        }
        return localDate;
    }

    public static Long timestampOf(LocalDateTime localDateTime){
        if(null == localDateTime){
            return null;
        }
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(ZONE));
    }


}
