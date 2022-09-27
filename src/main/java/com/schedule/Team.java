package com.schedule;

public class Team {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public boolean equals(Team otherTeam){
        return name.equals(otherTeam.name) && id== otherTeam.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
