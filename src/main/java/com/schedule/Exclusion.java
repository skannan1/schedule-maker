package com.schedule;

import java.util.Calendar;
import java.util.Date;

public class Exclusion {
    Date date;

    public Exclusion(Date date){
        this.date = date;
    }

    public boolean shouldExclude(Date date){
        return (this.date.equals(date));
    }
}
