package com.schedule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> getWeekends(LocalDate fromDate, LocalDate toDate){
        List<String> weekends = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        int fromDay = fromDate.getDayOfWeek().getValue();
        int daysToFirstWeekend = 6-fromDay;
        LocalDate nextWeekend = fromDate.plusDays(daysToFirstWeekend);
        while(nextWeekend.isBefore(toDate)){
            weekends.add(format.format(nextWeekend));
            if(nextWeekend.plusDays(1).isBefore(toDate)){
                weekends.add(format.format(nextWeekend.plusDays(1)));
            }
            nextWeekend = nextWeekend.plusDays(7);
        }
        return weekends;
    }

}
