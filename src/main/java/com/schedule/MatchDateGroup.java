package com.schedule;

import java.util.ArrayList;
import java.util.List;

public class MatchDateGroup {
    private List<MatchDate> dates = new ArrayList<>();

    public void addMatchDate(MatchDate date) {
        dates.add(date);
    }

    public List<MatchDate> getMatchDates(){
        return dates;
    }
}
