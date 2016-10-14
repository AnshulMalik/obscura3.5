package com.obscuraconflu.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obscuraconflu.api.dao.LevelDao;
import com.obscuraconflu.api.models.Level;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	LevelDao levelDao;
	
	@Override
	public Level getLevel(Long level, Long subLevel) {
		return levelDao.findByParentLevelAndLevel(level, subLevel);
	}

	@Override
	public String getAnswer(Long level, Long subLevel) {
		return levelDao.findByParentLevelAndLevel(level, subLevel).getAnswer();
	}
	
	@Override
	public String getAnswer(Long level) {
		return this.getAnswer(level, level);
	}

	@Override
	public Level getLevelById(Long id) {
		return levelDao.findById(id);
	}

	@Override
	public Level getLevelByUrl(String url) {
		return levelDao.findByUrl(url);
	}

	@Override
	public String getAnswerByUrl(String url) {
		Level l = this.getLevelByUrl(url);
		return this.getAnswer(l.getParentLevel(), l.getLevel());
	}

}
