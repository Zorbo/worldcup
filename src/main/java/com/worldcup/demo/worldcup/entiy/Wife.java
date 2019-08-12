package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Wife entity
 *
 * @author tamas.kiss
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Wife extends Person implements Serializable {

    private int freeTime;

    public Wife(String name) {
        super(name);
    }

    /**
     * Customized watchCup changing summing freeTime
     * @param cup The Cup
     */
    @Override
    public void watchCup(Cup cup) {
        this.freeTime += cup.getTotalTime();
        getWatchedCups().add(cup.getTeam1().getName() + cup.getTeam2().getName());
    }

}
