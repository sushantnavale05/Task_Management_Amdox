package com.TaskManagement1.DTO;

import java.util.Map;

public class WebhookEventDTO {
	
	private String eventType;
	private Map<String,Object> payload;
	
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Map<String, Object> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	
	

}

