package com.schedule.excl;


import com.schedule.excl.Exclusion;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class NoSatExclusion extends Exclusion {
    public NoSatExclusion() {
    }

    @Override
    public boolean shouldExclude(LocalDate date) {
        return (DayOfWeek.SATURDAY==date.getDayOfWeek());
    }
}
