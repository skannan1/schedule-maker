package com.schedule.base;

import java.util.UUID;

public class Team {
    private String name;
    private String id;

    public Team(){
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object otherTeam){
        if(otherTeam instanceof Team){
            return id.equals(((Team)otherTeam).id);
        }else return name.equals(otherTeam);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
