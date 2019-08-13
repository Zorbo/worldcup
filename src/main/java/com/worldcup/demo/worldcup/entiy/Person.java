package com.worldcup.demo.worldcup.entiy;

import com.worldcup.demo.worldcup.service.CupService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

/**
 * Person superclass entity
 *
 * @author tamas.kiss
 */
@Data
@ToString
@MappedSuperclass
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> watchedCups = new ArrayList<>();

    public abstract void watchCup(CupService cupService);
}
