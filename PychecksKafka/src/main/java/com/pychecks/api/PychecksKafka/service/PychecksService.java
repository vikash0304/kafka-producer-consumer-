package com.pychecks.api.PychecksKafka.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pychecks.api.PychecksKafka.entity.Pychecks;
import com.pychecks.api.PychecksKafka.model.PychecksRequest;
import com.pychecks.api.PychecksKafka.model.PychecksResponse;
import com.pychecks.api.PychecksKafka.producer.PychecksProducer;
import com.pychecks.api.PychecksKafka.repository.PychecksRepository;

@Service
public class PychecksService {

	Logger log = LoggerFactory.getLogger(PychecksService.class);
	
	@Autowired
	private PychecksRepository pychecksRepository;

	@Autowired
	private PychecksProducer pychecksProducer;

	public String publish(PychecksRequest pychecksRequest) {
		log.info("Received request EventType: {} and Message: {}", pychecksRequest.getEventType(),
				pychecksRequest.getMessage());
		return pychecksProducer.publish(pychecksRequest);
	}

	public List<PychecksResponse> fetchAll() {
		List<Pychecks> pychecks = pychecksRepository.findAll();
		List<PychecksResponse> response = new ArrayList<>();
		pychecks.forEach(p -> {
			PychecksResponse pychecksResponse = new PychecksResponse(p.getId(), p.getEventType(), p.getMessage(), p.getTimestamp());
			response.add(pychecksResponse);
		});
		return response;
	}

	public PychecksResponse fetchById(int id) {
		PychecksResponse response = null;
		Optional<Pychecks> pycheckOpt = pychecksRepository.findById(id);
		if(pycheckOpt.isPresent()) {
			Pychecks pychecks = pycheckOpt.get();
			response = new PychecksResponse(pychecks.getId(), pychecks.getEventType(), pychecks.getMessage(), pychecks.getTimestamp());
		}
		return response;
	}
}
