package com.obscuraconflu.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.ObUser;

@RestResource(exported = true)
public interface UserDao extends CrudRepository<ObUser, Long> {
	
	public ObUser findById(Long id);
	
	public ObUser findByEmail(String email);
	
	public ObUser findByToken(String token);
	
}