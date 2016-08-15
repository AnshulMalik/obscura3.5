package com.obscuraconflu.api.dto;

import java.io.Serializable;

public class Response implements Serializable {

	private int code = 200;

	private String message = "SUCCESS";
	
	public Response() {
		
	}
	
	public Response(int code, String me) {
		this.code = code;
		message = me;
	}
	
	public int getResponseCode() {
		return code;
	}

	public void setResponseCode(int responseCode) {
		this.code = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
