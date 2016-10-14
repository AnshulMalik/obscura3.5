package com.obscuraconflu.api.dto;

import java.io.Serializable;

public class LeaderboardUser implements Serializable{
	private Long rank;

	private String name;
	
	private Long level;
	
	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
	
	
}
