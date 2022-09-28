package com.schedule.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class Match {
    @Getter @Setter
    private Team teamA;
    @Getter @Setter
    private Team teamB;
    public Match(Team teamA, Team teamB){
        this.teamA = teamA;
        this.teamB = teamB;
    }
    public Match(Set<Team> teams){
        Team[] teamsArray = teams.toArray(new Team[]{});
        teamA = teamsArray[0];
        teamB = teamsArray[1];
    }
}
