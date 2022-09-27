package com.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Configuration {
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;
    private int matchDatesPerGroup;
    private int venueCount;
    private int slotsPerVenue;
    private List<MatchDateGroup> matchGroup = new ArrayList();

    public static Configuration getTestConfiguration() {
        Configuration config = new Configuration();
        config.setTournamentStart(LocalDate.of(2022,10,1));
        config.setTournamentEnd(LocalDate.of(2023,07,18));
        config.setVenueCount(2);
        config.setSlotsPerVenue(3);
        config.setMatchDatesPerGroup(2);
        List<LocalDate> weekends = Util.getWeekends(config.getTournamentStart(),config.getTournamentEnd());
        Iterator<LocalDate> iterator = weekends.iterator();
        while(iterator.hasNext()){
            MatchDateGroup group = new MatchDateGroup();
            MatchDate matchDate = new MatchDate();
            matchDate.setDate(iterator.next());
            group.addMatchDate(matchDate);
            if(iterator.hasNext()){
                MatchDate matchDate2 = new MatchDate();
                matchDate2.setDate(iterator.next());
                group.addMatchDate(matchDate2);
            }
            config.getMatchGroup().add(group);
        }
        return config;
    }
}
