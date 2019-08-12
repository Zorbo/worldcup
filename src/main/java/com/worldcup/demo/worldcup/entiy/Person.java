package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.Cup;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.ToString;

/**
 * Person superclass entity
 *
 * @author tamas.kiss
 */
@Data
@ToString
public abstract class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private List<String> watchedCups = new ArrayList<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public abstract void watchCup(Cup cup);
}
