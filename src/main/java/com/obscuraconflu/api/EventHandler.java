package com.obscuraconflu.api;

import static com.obscuraconflu.api.WebSocketConfiguration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.obscuraconflu.api.models.ObUser;

@Component
@RepositoryEventHandler(ObUser.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;
	
	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, 
				EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}
	
	@HandleAfterCreate
	public void newEmployee(ObUser user) {
		this.websocket.convertAndSend(
		  MESSAGE_PREFIX + "/newUser", getPath(user));
	}
	
	private String getPath(ObUser user) {
		return this.entityLinks.linkForSingleResource(user.getClass(),
				user.getId()).toUri().getPath();
	}

}
