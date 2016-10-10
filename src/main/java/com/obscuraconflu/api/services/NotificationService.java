package com.obscuraconflu.api.services;

import java.util.List;

import com.obscuraconflu.api.models.Notification;

public interface NotificationService {
	
	public Notification getNotification(Long id);
	
	public void markUnread(Long userId);
	
	public List<Notification> get(Long userId);
	
	public List<Notification> getAfter(Long userId, String createdAt);
}
