package com.worldcup.demo.worldcup;

import static org.mockito.Mockito.when;

import com.worldcup.demo.worldcup.entiy.Team;
import com.worldcup.demo.worldcup.exceptions.CupException;
import com.worldcup.demo.worldcup.repository.TeamRepository;
import com.worldcup.demo.worldcup.service.CupService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CupServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    private CupService cupService;

    @Test
    public void testGetTeam() {
        Optional<Team> team1 = Optional.of(new Team());
        team1.get().setName("Mo");
        team1.get().setId(Integer.toUnsignedLong(1));

        when(teamRepository.findById(Integer.toUnsignedLong(1))).thenReturn(team1);

        Assert.assertEquals(teamRepository.findById(Integer.toUnsignedLong(1)), team1);
    }

    @Test
    public void testTeamList() {
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("Mo");
        team2.setName("Ho");

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1 ,team2));
        cupService.createTeams();

        Assert.assertEquals(2, cupService.getTeamList().size());
    }

    @Test
    public void testSelectTeams() {
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("Mogyoro");
        team2.setName("Hovirag");

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1 ,team2));
        cupService.createTeams();
        cupService.selectTeam();

        Assert.assertTrue(cupService.getTeam1().getName().contains("o"));
        Assert.assertTrue(cupService.getTeam2().getName().contains("o"));
    }

    @Test(expected = CupException.class)
    public void testCupException() {
        Team team1 = new Team();
        team1.setName("Mo");

        when(teamRepository.findAll()).thenReturn(Collections.singletonList(team1));
        cupService.createTeams();
        cupService.selectTeam();
    }

}
