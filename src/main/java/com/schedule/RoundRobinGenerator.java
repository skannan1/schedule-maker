package com.schedule;

import java.util.*;

public class RoundRobinGenerator {

    public static void main(String[] args){
        List<Team> teams = new ArrayList<>();
        int count=1;
        while(count<=8){
            Team team = new Team();
            team.setName("Team"+count);
            count++;
            teams.add(team);
        }
        RoundRobinGenerator rg = new RoundRobinGenerator();
        System.out.println(rg.getMatchups(teams));
    }

    public List<Set<Team>> getMatchups(List<Team> teams){
        if(teams.size()%2!=0)
        {
            Team dummyTeam = new Team();
            dummyTeam.setName("No Game");
            teams.add(dummyTeam);
        }
        Collections.shuffle(teams);
        Team firstTeam = teams.get(0);
        List<Team> listA = new ArrayList();
        listA.addAll(teams.subList(0,teams.size()/2));
        List<Team> listB = new ArrayList();
        listB.addAll(teams.subList(teams.size()/2,teams.size()));


        List<Set<Team>> result = new ArrayList<>();
        for(int i=0;i< teams.size()-1;i++){
            for(int j=0;j<listA.size();j++) {
                HashSet<Team> set = new HashSet<>();
                set.add(listA.get(j));
                set.add(listB.get(j));
                result.add(set);
            }
            Team teamB = listB.remove(0);
            listA.add(1,teamB);
            Team lastTeamFromA = listA.remove(listA.size() - 1);
            listB.add(listB.size(),lastTeamFromA);
        }
        return result;
    }

    private void printStat(List<Team> sublist) {
        System.out.println("\n");
        for(int i=0;i<sublist.size();i++) {
            System.out.print(sublist.get(i));
            System.out.print("|");
        }
    }
}
