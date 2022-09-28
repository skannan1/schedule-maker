package com.schedule;

import com.schedule.base.Match;
import com.schedule.base.MatchDate;
import com.schedule.base.MatchGroup;
import com.schedule.base.Team;
import com.schedule.config.Configuration;
import com.schedule.excl.Exclusion;

import java.util.*;

public class RoundRobinGenerator {

    public static void main(String[] args){
        Configuration config = Configuration.getTestConfiguration();
        RoundRobinGenerator rg = new RoundRobinGenerator();
//        System.out.println(rg.getMatchups(config.getTeams()));
        setupMatches(config,rg.getMatchups(config.getTeams()));
    }

    private static void setupMatches(Configuration config, List<Set<Team>> matchups){
        ArrayDeque<Set<Team>> matches = new ArrayDeque<Set<Team>>();
        matches.addAll(matchups);
        ArrayDeque<MatchGroup> matchGroups = new ArrayDeque<>();
        matchGroups.addAll(config.getMatchGroup());
        while(matchGroups.peek()!=null){
            MatchGroup gp = matchGroups.pop();
            ArrayDeque<MatchDate> matchDates = new ArrayDeque<>();
            matchDates.addAll(gp.getMatchDates());
            while(matchDates.peek()!=null){
                MatchDate md = matchDates.pop();
                int slots = md.getTotalSlots();
                for(int i=0;i<slots;i++){
                    boolean slotFilled = false;
                    while(!slotFilled) {
                        Set<Team> match = matches.peek();
                        boolean rulesPassed = runExclusions(match,config,md);
                        if(rulesPassed){
                            md.addMatch(i,new Match(match));
                            matches.removeFirst();
                            slotFilled = true;
                        }else{
                            matches.addLast(matches.pop());
                        }
                    }
                }
            }
        }
    }

    private static boolean runExclusions(Set<Team> match,Configuration config,MatchDate date){
        Team[] teams = match.toArray(new Team[]{});
        for(Team team : teams) {
            Iterator<Exclusion> exclIterator = config.getExclusionMap().get(team).iterator();
            while (exclIterator.hasNext()) {
                Exclusion excl = exclIterator.next();
                if (!excl.shouldExclude(date.getDate())) continue;
                else return false;
            }
        }
        return true;
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
