package com.pychecks.api.PychecksKafkaConsumer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pychecks.api.PychecksKafkaConsumer.entity.FailedMessage;
import com.pychecks.api.PychecksKafkaConsumer.service.PychecksKafkaConsumerService;

@RestController
@RequestMapping("/pycheck-dlq")
public class PychecksKafkaConsumerController {

	Logger log = LoggerFactory.getLogger(PychecksKafkaConsumerController.class);
	
	@Autowired
	private PychecksKafkaConsumerService pychecksKafkaConsumerService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<FailedMessage>> fetchAll() {
		log.info("Fetching all pychecks event:");
		return ResponseEntity.ok().body(pychecksKafkaConsumerService.fetchAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FailedMessage> fetchById(@PathVariable int id) {
		log.info("Fetching pycheck failed event by Id: {}",id);
		return ResponseEntity.ok().body(pychecksKafkaConsumerService.fetchById(id));
	}
}
