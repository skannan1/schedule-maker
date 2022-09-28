package com.schedule.excl;


import com.schedule.excl.Exclusion;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class NoSunExclusion extends Exclusion {

    public NoSunExclusion() {
    }

    @Override
    public boolean shouldExclude(LocalDate date) {
        return (DayOfWeek.SUNDAY==date.getDayOfWeek());
    }
}
