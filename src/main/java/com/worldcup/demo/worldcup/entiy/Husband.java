package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.CupService;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Husband entity
 *
 * @author tamas.kiss
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Husband extends Person implements Serializable {

    private int beers;

    public Husband() {
    }

    public Husband(String name) {
        super(name);
    }

    /**
     * Customized watchCup changing beer count
     * @param cupService The Cup
     */
    @Override
    public void watchCup(CupService cupService) {
        Random random = new Random();
        if (cupService.isItGood()) {
            this.beers += random.nextInt(8) + 3;
        } else {
            this.beers += random.nextInt(6);
        }
        getWatchedCups().add(cupService.getTeam1().getName() + cupService.getTeam2().getName());
    }

}
