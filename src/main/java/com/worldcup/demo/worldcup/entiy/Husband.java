package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Husband extends Person implements Serializable {

    private int beers;

    private List<Cup> watchedCups;

    private Random random = new Random();

    public Husband(String name) {
        super(name);
    }

    @Override
    public void watchCup(Cup cup) {
        if (cup.isCupGood()) {
            this.beers += random.nextInt(8) + 3;
        } else {
            this.beers += random.nextInt(6);
        }
    }

}
