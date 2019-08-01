package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.service.Cup;
import org.junit.Assert;
import org.junit.Test;

public class CupTest {



    private final static String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
    private Cup cup = new Cup(DATA);

    @Test
    public void testTeamList() {
        Assert.assertEquals(4, cup.getTeamList().size());
    }

    @Test
    public void testSelectTeams() {
        System.out.println(cup.getTeam1().getName());
        System.out.println(cup.getTeam2().getName());
        System.out.println(cup.getTotalTime());
    }

}
