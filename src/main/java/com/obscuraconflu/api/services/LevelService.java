package com.obscuraconflu.api.services;

import com.obscuraconflu.api.models.Level;

public interface LevelService {
	
	public Level getLevel(Long level, Long subLevel);
	
	public Level getLevelById(Long id);
	
	public Level getLevelByUrl(String url);
	
	public String getAnswerByUrl(String url);
	
	public String getAnswer(Long level, Long subLevel);

	public String getAnswer(Long level);

}
