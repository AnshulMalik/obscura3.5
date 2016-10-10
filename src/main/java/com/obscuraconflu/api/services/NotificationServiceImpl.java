package com.obscuraconflu.api.services;

import java.util.List;

import com.obscuraconflu.api.models.Notification;
import com.obscuraconflu.api.dao.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired NotificationDao notificationDao;
	@Override
	public Notification getNotification(Long id) {
		return notificationDao.findById(id);
	}

	@Override
	public void markUnread(Long userId) {
		notificationDao.markRead(userId);
	}

	@Override
	public List<Notification> get(Long userId) {
		return notificationDao.getTop(userId);
	}

	@Override
	public List<Notification> getAfter(Long userId, String createdAt) {
		return notificationDao.getTopAfterTime(userId, createdAt);
	}

}
