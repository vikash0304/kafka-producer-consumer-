package com.pychecks.api.PychecksKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pychecks.api.PychecksKafka.entity.Pychecks;

@Repository
public interface PychecksRepository extends JpaRepository<Pychecks, Integer> {

}
