package com.axioma.axiomatrainee.utill;

import java.time.Instant;
import java.util.Date;

public class DateParser {

    public static Date parseFromUnix(Long unixTimestamp) {
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        return Date.from(instant);
    }

    public static Long parseFromDate(Date date) {
        if(date == null) {
            return 0L;
        }
        return date.getTime()/1000;
    }
}
