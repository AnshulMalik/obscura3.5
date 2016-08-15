package com.obscuraconflu.api.constant;

public class ErrorCode {

	private int code;;
	private String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorCodes [code=" + code + ", message=" + message + "]";
	}

}
