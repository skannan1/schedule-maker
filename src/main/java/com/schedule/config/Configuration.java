package com.schedule.config;

import com.schedule.base.MatchDate;
import com.schedule.base.MatchGroup;
import com.schedule.base.Team;
import com.schedule.util.Util;
import com.schedule.excl.Exclusion;
import com.schedule.excl.NoSatExclusion;
import com.schedule.excl.NoSunExclusion;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Configuration {
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;
    private int matchDatesPerGroup;
    private int venueCount;
    private int slotsPerVenue;
    private List<MatchGroup> matchGroup = new ArrayList();
    private List<Team> teams = new ArrayList();
    private HashMap<Team,List<Exclusion>> exclusionMap = new HashMap();
    private HashMap<String,Team> teamMap = new HashMap();

    public static Configuration getTestConfiguration() {
        Configuration config = new Configuration();
        config.setTournamentStart(LocalDate.of(2022,10,1));
        config.setTournamentEnd(LocalDate.of(2023,07,18));
        config.setVenueCount(2);
        config.setSlotsPerVenue(3);
        config.setMatchDatesPerGroup(2);
        List<Team> teams = config.getTeams();
        int count=1;
        while(count<=8){
            Team team = new Team();
            team.setName("Team"+count);
            count++;
            teams.add(team);
            config.getExclusionMap().put(team, new ArrayList());
            config.getTeamMap().put(team.getName(),team);
        }
        List<LocalDate> weekends = Util.getWeekends(config.getTournamentStart(),config.getTournamentEnd());
        Iterator<LocalDate> iterator = weekends.iterator();
        while(iterator.hasNext()){
            MatchGroup group = new MatchGroup();
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
        config.buildExclusion();
        return config;
    }

    private void buildExclusion(){
        String fileName = "exclusions.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("=");
                List<Exclusion> exclusions = exclusionMap.get(teamMap.get(split[0]));
                String[] excl = split[1].split(",");
                for(String ex:excl){
                    if("NO_SAT".equals(ex)){
                            exclusions.add(new NoSatExclusion());
                            continue;
                    }
                    if("NO_SUN".equals(ex)){
                        exclusions.add(new NoSunExclusion());
                        continue;
                    }
                    DateTimeFormatter  df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate ld = LocalDate.parse(ex,df);
                    exclusions.add(new Exclusion(ld));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
