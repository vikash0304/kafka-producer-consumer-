package com.pychecks.api.PychecksKafkaConsumer.model;

public class PychecksRequest {

	private String eventType;

	private String message;

	public PychecksRequest() {
		super();
	}

	public PychecksRequest(String eventType, String message) {
		super();
		this.eventType = eventType;
		this.message = message;
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

	@Override
	public String toString() {
		return "PychecksRequest [eventType=" + eventType + ", message=" + message + "]";
	}
}
