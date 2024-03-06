package com.pychecks.api.PychecksKafkaConsumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pychecks.api.PychecksKafkaConsumer.entity.FailedMessage;
import com.pychecks.api.PychecksKafkaConsumer.repository.FailedMessageRepository;

@Service
public class PychecksKafkaConsumerService {

	@Autowired
	private FailedMessageRepository failedMessageRepository;
	
	public List<FailedMessage> fetchAll() {
		// TODO Auto-generated method stub
		return failedMessageRepository.findAll();
	}

	public FailedMessage fetchById(int id) {
		return failedMessageRepository.getById(id);
	}
}
