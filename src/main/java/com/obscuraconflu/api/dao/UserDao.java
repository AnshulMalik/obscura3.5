package com.obscuraconflu.api.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.ObUser;

//@RestResource(exported = false)
public interface UserDao extends PagingAndSortingRepository<ObUser, Long> {
	
	public ObUser findById(Long id);
}