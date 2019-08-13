package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.CupService;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "wife")
public class Wife extends Person {

    private Integer freeTime;

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
