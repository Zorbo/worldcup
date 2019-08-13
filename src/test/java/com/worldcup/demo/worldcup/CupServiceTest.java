package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.repository.TeamRepository;
import com.worldcup.demo.worldcup.service.CupService;
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
    public void testTeamList() {
        Assert.assertEquals(4, cupService.getTeamList().size());
    }

    @Test
    public void testSelectTeams() {
        System.out.println(cupService.getTeam1().getName());
        System.out.println(cupService.getTeam2().getName());
        System.out.println(cupService.getTotalTime());
    }

}
