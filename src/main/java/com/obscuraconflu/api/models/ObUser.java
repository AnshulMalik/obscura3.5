package com.obscuraconflu.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.obscuraconflu.api.dto.SignUpRequest;
import com.obscuraconflu.api.dto.SocialLoginRequest;

@Table(name = "user")
@Entity
public class ObUser {

	private @Id @GeneratedValue Long id;

	private String firstName;

	private String lastName;

	@Email
	private String email;

	private String phone;

	private Long level; 
	
	private Long parentLevel;

	private String levelUrl;
	
	private String password;
	
	private String token;

	private @JsonIgnore String accessToken;

	private @JsonIgnore String signupType;

	private @JsonIgnore Date createdAt;

	private Date updatedAt;
	
	private @JsonIgnore  String uid;
	
	private ObUser() {
		
	}

	public ObUser(String firstName, String lastName, String email, String phone, Long level, Long parentLevel, String levelUrl, String password, String token, String accessToken, String signupType, String uid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.level = level;
		this.parentLevel = parentLevel;
		this.levelUrl = levelUrl;
		this.password = password;
		this.token = token;
		this.accessToken = accessToken;
		this.signupType = signupType;
		this.uid = uid;
	}
	
	public ObUser(SignUpRequest signupRequest, String token) {
		this.firstName = signupRequest.getFirstName();
		this.lastName = signupRequest.getLastName();
		this.email = signupRequest.getEmail();
		this.phone = signupRequest.getPhone();
		this.level = 0L;
		this.parentLevel = 0L;
		this.levelUrl = "0";
		this.password = signupRequest.getPassword();
		this.token = token;
		this.accessToken = signupRequest.getAccessToken();
		this.signupType = signupRequest.getSignupType();
		this.uid = signupRequest.getUid();
		
	}
	
	public ObUser(SocialLoginRequest request, String token) {
		this.firstName = request.getName();
		this.email = request.getEmail();
		this.level = 0L;
		this.levelUrl = "0"; 
		this.parentLevel = 0L;
		this.token = token;
		this.accessToken = request.getAccessToken();
		if(request.getType() == 0)
			this.signupType = "FACEBOOK";
		else 
			this.signupType = "GOOGLE";
		this.uid = request.getUid();
		
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

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(Long parentLevel) {
		this.parentLevel = parentLevel;
	}

	public String getLevelUrl() {
		return levelUrl;
	}

	public void setLevelUrl(String levelUrl) {
		this.levelUrl = levelUrl;
	}
}