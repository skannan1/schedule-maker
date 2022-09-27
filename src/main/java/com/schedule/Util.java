package com.schedule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<LocalDate> getWeekends(LocalDate fromDate, LocalDate toDate){
        List<LocalDate> weekends = new ArrayList<>();
        int fromDay = fromDate.getDayOfWeek().getValue();
        int daysToFirstWeekend = 6-fromDay;
        LocalDate nextWeekend = fromDate.plusDays(daysToFirstWeekend);
        while(nextWeekend.isBefore(toDate)){
            weekends.add(nextWeekend);
            if(nextWeekend.plusDays(1).isBefore(toDate)){
                weekends.add(nextWeekend.plusDays(1));
            }
            nextWeekend = nextWeekend.plusDays(7);
        }
        return weekends;
    }

}
