package com.schedule.excl;

import java.time.LocalDate;
import java.util.Calendar;

public class Exclusion {
    LocalDate date;
    public Exclusion(){}
    public Exclusion(LocalDate date){
        this.date = date;
    }

    public boolean shouldExclude(LocalDate date){
        if(date==null) return false;
        return (this.date.equals(date));
    }
}
