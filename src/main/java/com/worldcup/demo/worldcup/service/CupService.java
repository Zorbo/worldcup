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
import org.springframework.stereotype.Service;

/**
 * This class responsible to create Cup
 *
 * @author tamas.kiss
 */
@Data
@Service("cupService")
public class CupService {

    private static final Logger logger = LoggerFactory.getLogger(CupService.class);

    private List<Team> teamList;
    private int totalTime;
    private Team team1;
    private Team team2;
    private Random random = new Random();
    private boolean isItGood;

    private final TeamRepository teamRepository;

    @Autowired
    public CupService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.totalTime = 90 + random.nextInt((15 + 1));
        this.isItGood = random.nextBoolean();
    }

    /**
     * Create teams from the input data and init the teamList
     */
    public void createTeams() {
        teamRepository.findAll().forEach(this.teamList::add);
    }

    /**
     * Init the two teams
     */
    public void selectTeam() {
        boolean match = true;
        if (teamList.isEmpty() || teamList.size() == 1) {
            logger.warn("There is not enough Teams in the list");
            throw new TeamException("Team list size: " + teamList.size());
        }
        this.team1 = teamList.get(random.nextInt(teamList.size()));
        this.team2 = teamList.get(random.nextInt(teamList.size()));
        while (match) {
            if (team1.getName().equals(team2.getName())) {
                this.team2 = teamList.get(random.nextInt(teamList.size()));
            } else {
                match = false;
            }
        }
    }

}
