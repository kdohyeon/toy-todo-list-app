package org.example.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeUtils {

    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static ZonedDateTime of(String datetime) {
        return ZonedDateTime.of(LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS)), ZoneId.of("Asia/Seoul"));
    }

    public static String of(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }
}
