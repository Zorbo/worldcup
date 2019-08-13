package com.worldcup.demo.worldcup.repository;

import com.worldcup.demo.worldcup.entiy.Wife;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WifeRepository extends CrudRepository<Wife, Long> {

}
