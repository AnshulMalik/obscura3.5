package com.obscuraconflu.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "notification")
@Entity
public class Notification {
	
	private @Id @GeneratedValue Long id;

	private Long toId;

	private String body;

	private String status;

	private @JsonIgnore String createdAt;

	private @JsonIgnore String updatedAt;

	public Notification() {

	}

	public Notification(Long to, String body, String status) {
		this.toId = to;
		this.body = body;
		this.status = status; 
	}

	public Long getToId() {
		return toId;
	}

	public void setToId(Long to) {
		this.toId = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
