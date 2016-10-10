package com.obscuraconflu.api.dto;

import java.util.Date;

import com.obscuraconflu.api.models.ObUser;

public class LoginResponse extends Response{

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long level;
	
	private Long parentLevel;
	
	private String token;
	
	private Date updatedAt;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(Long parentLevel) {
		this.parentLevel = parentLevel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LoginResponse(ObUser user) {
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.level = user.getLevel();
		this.parentLevel = user.getParentLevel();
		this.token = user.getToken();
		this.updatedAt = user.getUpdatedAt();
	}
}
