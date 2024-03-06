package com.pychecks.api.PychecksKafka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pychecks.api.PychecksKafka.model.PychecksRequest;
import com.pychecks.api.PychecksKafka.model.PychecksResponse;
import com.pychecks.api.PychecksKafka.service.PychecksService;

@RestController
@RequestMapping("/pycheck")
public class PychecksController {
	
	Logger log = LoggerFactory.getLogger(PychecksController.class);

	@Autowired
	private PychecksService pychecksService;
	
	@PostMapping(value = "/publish")
	public ResponseEntity<String> publish(@RequestBody PychecksRequest pychecksRequest) {
		log.info("Publishing the event: {}", pychecksRequest);
		return ResponseEntity.ok().body(pychecksService.publish(pychecksRequest));
	}
	
	@GetMapping(value = "")
	public ResponseEntity<List<PychecksResponse>> fetchAll() {
		log.info("Fetching all pychecks event:");
		return ResponseEntity.ok().body(pychecksService.fetchAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PychecksResponse> fetchById(@PathVariable int id) {
		log.info("Fetching pycheck event by Id: {}",id);
		return ResponseEntity.ok().body(pychecksService.fetchById(id));
	}
}
