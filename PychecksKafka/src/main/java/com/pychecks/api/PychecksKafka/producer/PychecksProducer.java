package com.pychecks.api.PychecksKafka.producer;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pychecks.api.PychecksKafka.model.PychecksRequest;

@Component
public class PychecksProducer {

	Logger log = LoggerFactory.getLogger(PychecksProducer.class);

	@Value(value = "${kafka.topic}")
	private String topic;

	private ObjectMapper objectMapper = new ObjectMapper();

	private final KafkaTemplate<String, String> kafkaTemplate;

	public PychecksProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public String publish(PychecksRequest pychecksRequest) {
		try {
			String message = objectMapper.writeValueAsString(pychecksRequest);
			ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, pychecksRequest.getEventType(),
					message);
			getHeaders(pychecksRequest.getEventType()).forEach((k, v) -> {
				producerRecord.headers().add(k, v.getBytes(StandardCharsets.UTF_16));
			});
			log.info("Message sent: " + pychecksRequest);
			kafkaTemplate.send(producerRecord);
			return "Success";
		} catch (JsonProcessingException e) {
			return "Failed: " + e.getMessage();
		}
	}

	private Map<String, String> getHeaders(String eventType) {
		Map<String, String> headers = new HashMap<>();
		headers.put("eventType", eventType);
		return headers;
	}
}
