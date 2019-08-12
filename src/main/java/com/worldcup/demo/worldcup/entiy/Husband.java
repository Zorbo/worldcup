package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.io.Serializable;
import java.util.Random;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Husband entity
 *
 * @author tamas.kiss
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Husband extends Person implements Serializable {

    private int beers;

    public Husband(String name) {
        super(name);
    }

    /**
     * Customized watchCup changing beer count
     * @param cup The Cup
     */
    @Override
    public void watchCup(Cup cup) {
        Random random = new Random();
        if (cup.isItGood()) {
            this.beers += random.nextInt(8) + 3;
        } else {
            this.beers += random.nextInt(6);
        }
    }

}
