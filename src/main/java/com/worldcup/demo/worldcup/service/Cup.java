package com.worldcup.demo.worldcup.service;

import static com.worldcup.demo.worldcup.service.ProcessData.getLine;

import com.worldcup.demo.worldcup.entiy.Team;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Cup {

    private List<Team> teamList;

    private int totalTime;

    private Team team1;

    private Team team2;

    private Random random = new Random();

    private boolean isItGood;

    public Cup(String inputData) {
        this.totalTime = 90 + random.nextInt((15 + 1));
        this.isItGood = random.nextBoolean();
        createTeams(inputData);
        selectTeam(this.teamList);
    }

    private void createTeams(String inputData) {
        this.teamList = Arrays.stream(getLine(inputData, 2)
                                          .split("\\s*,\\s*")).map(Team::new).collect(Collectors.toList());
    }

    private void selectTeam(List<Team> teams) {
        boolean match = true;
        this.team1 = teams.get(random.nextInt(teams.size()));
        this.team2 = teams.get(random.nextInt(teams.size()));
        while (match) {
            if (team1.getName().equals(team2.getName())) {
                this.team2 = teams.get(random.nextInt(teams.size()));
            } else {
                match = false;
            }
        }
    }

}
