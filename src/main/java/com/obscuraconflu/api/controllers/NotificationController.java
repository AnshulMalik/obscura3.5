package com.obscuraconflu.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.services.NotificationService;

@RestController
public class NotificationController {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(LevelController.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/notification/{id}", produces = "application/json")
	public Response getNotification() {
		// TODO
		// An after parameter, which will fetch notifications after certain time
		LOG.info("Request recieved to get notifications ");
		notificationService.get(1L);
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public Response markNotificationsAsRead() {
		
		return null;
	}	
}