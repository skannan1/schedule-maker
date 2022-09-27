package com.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MatchDate {
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private int totalSlots;
    private HashMap<Integer,List<Match>> matches = new HashMap<>();

    public void addMatch(int slot, Match match){
        List<Match> _matches = matches.getOrDefault(slot, new ArrayList<Match>());
        if(_matches.size()>=totalSlots)
          throw new RuntimeException("Slots exceeded for day "+date);
        _matches.add(match);
        matches.put(slot,_matches);
    }

    public void removeLastMatch(){
        matches.remove(matches.size()-1);
    }

    public List<Match> getMatches(int slot){
        return matches.get(slot);
    }
}
