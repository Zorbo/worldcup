package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.CupService;
import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Wife entity
 *
 * @author tamas.kiss
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Wife extends Person implements Serializable {

    private int freeTime;

    public Wife() {
    }

    public Wife(String name) {
        super(name);
    }

    /**
     * Customized watchCup changing summing freeTime
     * @param cupService The Cup
     */
    @Override
    public void watchCup(CupService cupService) {
        this.freeTime += cupService.getTotalTime();
        getWatchedCups().add(cupService.getTeam1().getName() + cupService.getTeam2().getName());
    }

}
