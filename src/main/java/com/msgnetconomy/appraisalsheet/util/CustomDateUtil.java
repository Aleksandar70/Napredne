package com.msgnetconomy.appraisalsheet.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CustomDateUtil {

    private CustomDateUtil() {
    }

    public static LocalDate getLocalDateFromDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date getDateFromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date getTodayPlusDaysToAdd(int daysToAdd) {
        return getDateFromLocalDate(LocalDate.now().plusDays(daysToAdd));
    }
}
