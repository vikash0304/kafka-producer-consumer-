package com.pychecks.api.PychecksKafka;

import java.time.LocalDate;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pychecks.api.PychecksKafka.entity.Pychecks;
import com.pychecks.api.PychecksKafka.model.PychecksRequest;
import com.pychecks.api.PychecksKafka.repository.PychecksRepository;

@Service
public class PychecksKafkaListener {
	
	Logger log = LoggerFactory.getLogger(PychecksKafkaListener.class);
	
	@Autowired
	private PychecksRepository pychecksRepository;

	@KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
	public void listen(ConsumerRecord<String, PychecksRequest> consumerRecord) {

		log.info("Started consuming message on topic: {} offset: {} message: {}",consumerRecord.topic() , consumerRecord.offset(),  consumerRecord.value());
		if (consumerRecord.value().getEventType() == null || consumerRecord.value().getEventType().equalsIgnoreCase("NONE") || consumerRecord.offset() % 2 != 0) {
			log.error("Finished consuming message on topic: " + consumerRecord.topic() + ", offset: "
					+ consumerRecord.offset() + ", message: " + consumerRecord.value());
			throw new RuntimeException("Invalid Event Type");
		} else {
			Pychecks pychecks = new Pychecks(consumerRecord.value().getEventType(), consumerRecord.value().getMessage(), LocalDate.now());
			pychecksRepository.save(pychecks);
		}
	}
}
