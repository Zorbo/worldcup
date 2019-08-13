package com.worldcup.demo.worldcup.service;

import com.worldcup.demo.worldcup.entiy.Team;
import com.worldcup.demo.worldcup.exceptions.TeamException;
import com.worldcup.demo.worldcup.repository.TeamRepository;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class responsible to create Cup
 *
 * @author tamas.kiss
 */
@Data
public class CupService {

    private List<Team> teamList;
    private int totalTime;
    private Team team1;
    private Team team2;
    private Random random = new Random();
    private boolean isItGood;
    private static final Logger logger = LoggerFactory.getLogger(CupService.class);

    @Autowired
    TeamRepository teamRepository;

    /**
     * Init Cup object
     */
    public CupService() {
        this.totalTime = 90 + random.nextInt((15 + 1));
        this.isItGood = random.nextBoolean();
        createTeams();
        selectTeam(this.teamList);
    }

    /**
     * Create teams from the input data and init the teamList
     */
    private void createTeams() {
//        this.teamList = Arrays.stream(getLine(inputData, 2)
//                                          .split("\\s*,\\s*")).map(Team::new).collect(Collectors.toList());
        teamRepository.findAll().forEach(this.teamList::add);
    }

    /**
     * Init the two teams
     * @param teams The input Team list
     */
    private void selectTeam(List<Team> teams) {
        boolean match = true;
        if (teams.isEmpty() || teams.size() == 1) {
            logger.warn("There is not enough Teams in the list");
            throw new TeamException("Team list size: " + teams.size());
        }
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
