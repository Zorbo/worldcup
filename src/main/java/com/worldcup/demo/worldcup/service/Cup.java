package com.worldcup.demo.worldcup.service;

import com.worldcup.demo.worldcup.entiy.Team;
import java.util.Random;
import lombok.Data;

@Data
public class Cup {

    private int playTime;

    private int extraTimeMax;

    private int totalTime;

    private Team team1;

    private Team team2;

    private Random random = new Random();

    public Cup(int playTime, int extraTimeMax, Team team1, Team team2) {
        this.playTime = playTime;
        this.extraTimeMax = extraTimeMax;
        this.team1 = team1;
        this.team2 = team2;
        this.totalTime = playTime + random.nextInt((extraTimeMax + 1));
    }

    public boolean isCupGood() {
        return random.nextBoolean();
    }
}
