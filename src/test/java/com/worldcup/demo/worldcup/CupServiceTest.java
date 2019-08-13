package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.service.CupService;
import org.junit.Assert;
import org.junit.Test;

public class CupServiceTest {



    private final static String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
    private CupService cupService = new CupService();

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
