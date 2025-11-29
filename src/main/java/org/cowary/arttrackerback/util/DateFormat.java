package org.cowary.arttrackerback.util;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public enum DateFormat {
    ddMMyyyy("dd.MM.yyyy"),
    ddMMyyyyHHmm("dd.MM.yyyy HH:mm"),
    yyyyMMddHHmmNoDots("yyyy MM dd HH mm"),
    HTML("yyyy-MM-dd'T'HH:mm"),
    HTMLshort("yyyy-MM-dd"),
    CRON("mm HH dd MM '*");

    public static final String HTML_PATTERN = "yyyy-MM-dd'T'HH:mm";
    public static final String HTMLshort_PATTER = "yyyy-MM-dd";

    private final String pattern;
    private final ThreadLocal<DateTimeFormatter> format;


    DateFormat(String pattern) {
        this.pattern = pattern;
        this.format = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern(pattern));
    }

    public LocalDate parse(String str) {
        return LocalDate.parse(str, format.get());
    }

//    public String format(Date date) {
//        return format.get().format(date);
//    }

//    public String formatNow() {
//        return format.get().format(LocalDate.now());
//    }
//
//    public String formatThen(int diff, TimeUnit unit) {
//        return format(DateUtil.then(diff, unit));
//    }
}


