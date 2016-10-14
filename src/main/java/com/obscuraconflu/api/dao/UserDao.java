package com.obscuraconflu.api.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.ObUser;

@RestResource(exported = true)
public interface UserDao extends CrudRepository<ObUser, Long> {
	
	public ObUser findById(Long id);
	
	public ObUser findByEmail(String email);
	
	public ObUser findByToken(String token);

	@Query(value = "CALL GetRank(:id)", nativeQuery = true)
	public List<BigInteger> getRank(@Param("id") BigInteger id); 
	
	@Query(value = "SELECT * from user ORDER BY parent_level DESC, updated_at DESC", nativeQuery = true)
	public List<ObUser> getAllUsers(); 
	
	
}