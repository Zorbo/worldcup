package com.worldcup.demo.worldcup.repository;

import com.worldcup.demo.worldcup.entiy.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
