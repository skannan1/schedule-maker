package com.schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MatchDate {
    private LocalDate date;
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
        return matches;
    }
}
