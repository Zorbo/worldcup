package com.worldcup.demo.worldcup.repository;

import com.worldcup.demo.worldcup.entiy.Husband;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HusbandRepository extends CrudRepository<Husband, Long> {

}
