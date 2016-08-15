package com.obscuraconflu.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "user")
@Entity
public class ObUser {

	private @Id @GeneratedValue Long id;

	private String firstName;

	private String lastName;

	@Email
	private String email;

	private String phone;

	private int level;

	private String password;

	private @JsonIgnore String accessToken;

	private @JsonIgnore String signupType;

	private @JsonIgnore Date createdAt;

	private Date updatedAt;
	
	private @JsonIgnore  String uid;
	
	private ObUser() {
		
	}

	public ObUser(String firstName, String lastName, String email, int level, String signupType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.level = level;
		this.signupType = signupType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSignupType() {
		return signupType;
	}

	public void setSignupType(String signupType) {
		this.signupType = signupType;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updated) {
		this.updatedAt = updated;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date created) {
		this.createdAt = created;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}