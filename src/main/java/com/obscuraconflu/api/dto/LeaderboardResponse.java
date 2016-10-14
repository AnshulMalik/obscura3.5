package com.obscuraconflu.api.dto;

import java.util.List;

public class LeaderboardResponse extends Response{
	
	List<LeaderboardUser> users;
	
	public LeaderboardResponse() {
		
	}
	
	public List<LeaderboardUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<LeaderboardUser> users) {
		this.users = users;
	}
	
	public LeaderboardResponse(List<LeaderboardUser> users) {
		this.users = users;
	}
	
}
