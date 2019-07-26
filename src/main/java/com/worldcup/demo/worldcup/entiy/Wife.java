package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Wife extends People {

    private int freeTime;

    private List<Cup> watchedCups;

    private Random random = new Random();

    public Wife(String name) {
        super(name);
    }

    @Override
    public void watchCup(Cup cup) {
        this.freeTime += cup.getTotalTime();
    }

}
