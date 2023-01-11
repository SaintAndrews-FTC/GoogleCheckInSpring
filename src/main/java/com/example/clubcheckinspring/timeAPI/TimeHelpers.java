package com.example.clubcheckinspring.timeAPI;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHelpers {

    public static LocalDateTime getCurrenDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        String returnDate = String.valueOf(localDateTime);
    //    System.out.println(returnDate);
        return localDateTime;
    }

    public static String timeDifference(LocalDateTime startDate, LocalDateTime endDate) {
        Duration dur = Duration.between(startDate, endDate);
        String result = String.format("%d:%02d", dur.toHours(), dur.toMinutesPart());
        return result;
    }
}
