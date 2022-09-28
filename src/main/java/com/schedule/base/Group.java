package com.schedule.base;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team){
        teams.add(team);
    }
    public void removeTeam(Team team){
        teams.remove(team);
    }

    public int teamsCount(){
        return teams.size();
    }
}
