package com.obscuraconflu.api.dto;

public class SubmitAnswerRequest extends Response {
	private String token;

	private Long level;

	private Long subLevel;

	private String answer;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getSubLevel() {
		return subLevel;
	}

	public void setSubLevel(Long subLevel) {
		this.subLevel = subLevel;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
