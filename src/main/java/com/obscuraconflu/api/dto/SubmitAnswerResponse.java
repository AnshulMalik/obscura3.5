package com.obscuraconflu.api.dto;

public class SubmitAnswerResponse extends Response{

	private String nextLevel;
	
	private String nextSubLevel;

	public String getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(String nextLevel) {
		this.nextLevel = nextLevel;
	}

	public String getNextSubLevel() {
		return nextSubLevel;
	}

	public void setNextSubLevel(String nextSubLevel) {
		this.nextSubLevel = nextSubLevel;
	}
}
