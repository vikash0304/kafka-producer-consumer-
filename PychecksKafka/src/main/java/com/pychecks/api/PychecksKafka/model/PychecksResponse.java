package com.pychecks.api.PychecksKafka.model;

import java.time.LocalDate;

public class PychecksResponse {

	private Integer id;

	private String eventType;

	private String message;

	private LocalDate timestamp;

	public PychecksResponse() {
		super();
	}

	public PychecksResponse(Integer id, String eventType, String message, LocalDate timestamp) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
}
