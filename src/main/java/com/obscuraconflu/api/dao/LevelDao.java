package com.obscuraconflu.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.Level;

@RestResource(exported = true)
public interface LevelDao extends CrudRepository<Level, Long>{
	
	public Level findById(Long id);
	
	public Level findByParentLevelAndLevel(Long parentLevel, Long level);
}
