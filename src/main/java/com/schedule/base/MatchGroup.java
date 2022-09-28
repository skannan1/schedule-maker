package com.schedule.base;

import java.util.ArrayList;
import java.util.List;

public class MatchGroup {
    private List<MatchDate> dates = new ArrayList<>();

    public void addMatchDate(MatchDate date) {
        dates.add(date);
    }

    public List<MatchDate> getMatchDates(){
        return dates;
    }
}
