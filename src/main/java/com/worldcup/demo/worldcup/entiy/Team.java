package com.worldcup.demo.worldcup.entiy;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Team {

    private String name;

    public Team(String name) {
        this.name = name;
    }
}
