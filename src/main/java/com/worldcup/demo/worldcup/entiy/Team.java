package com.worldcup.demo.worldcup.entiy;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.ToString;

/**
 * Team entity
 *
 * @author tamas.kiss
 */
@Data
@ToString
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }
}
