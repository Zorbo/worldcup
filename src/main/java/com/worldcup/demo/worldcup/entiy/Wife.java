package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Wife extends Person implements Serializable {

    private int freeTime;

    public Wife(String name) {
        super(name);
    }

    @Override
    public void watchCup(Cup cup) {
        this.freeTime += cup.getTotalTime();
    }

}
