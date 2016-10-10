package com.obscuraconflu.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.Notification;

@RestResource(exported = true)
public interface NotificationDao extends CrudRepository<Notification, Long>{

	public Notification findById(Long id);
	
	@Query(value = "UPDATE notification SET status='READ' WHERE to_id = :userId", nativeQuery = true)
	public void markRead(@Param("userId") Long userId);
	
	@Query(value = "SELECT * FROM notification WHERE to_id = :userId ORDER BY updated_at LIMIT 10", nativeQuery = true)
	public List<Notification> getTop(@Param("userId") Long userId);
	
	@Query(value = "SELECT * FROM notification WHERE to_id = :userId AND created_at > :createdAt ORDER BY updated_at LIMIT 10", nativeQuery = true)
	public List<Notification> getTopAfterTime(@Param("userId") Long userId, @Param("createdAt") String createdAt);
	
}
