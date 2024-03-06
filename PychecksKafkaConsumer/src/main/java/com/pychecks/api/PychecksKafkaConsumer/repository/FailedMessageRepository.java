package com.pychecks.api.PychecksKafkaConsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pychecks.api.PychecksKafkaConsumer.entity.FailedMessage;

@Repository
public interface FailedMessageRepository extends JpaRepository<FailedMessage, Integer> {

}
