package com.schedule.base;

import java.util.Date;

public class Schedule {
    private Date date;
    private Team homeTeam;
    private Team roadTeam;
    private int hours;
    private int mins;

    public boolean equals(Schedule other) {
        return ((homeTeam.equals(other.homeTeam)|| homeTeam.equals(other.roadTeam) )&&
        roadTeam.equals(other.homeTeam)|| roadTeam.equals(other.roadTeam));
    }

    public String toString(){
        String a=homeTeam.getName();
        String b=roadTeam.getName();
        int compare = a.compareTo(b);

        return date.getTime()+","+(compare<0?homeTeam.getName()+","+roadTeam.getName():
            roadTeam.getName()+","+homeTeam.getName());
    }
}
