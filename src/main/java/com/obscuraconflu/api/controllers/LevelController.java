package com.obscuraconflu.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.obscuraconflu.api.dto.LevelResponse;
import com.obscuraconflu.api.dto.LoginResponse;
import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.models.Level;
import com.obscuraconflu.api.models.ObUser;
import com.obscuraconflu.api.services.LevelService;
import com.obscuraconflu.api.services.UserService;
import com.obscuraconflu.api.constant.ErrorConstants;


@RestController
public class LevelController {

	private static final Logger LOG = LoggerFactory.getLogger(LevelController.class);

	@Autowired
	LevelService levelService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/level/{id}", produces = "application/json")
	public Response getLevel(@PathVariable("id") Long id, @RequestHeader("authToken") String token) {

		ObUser user = userService.findByToken(token);
		
		if(user == null || (user.getParentLevel() < id)) {
			return ErrorConstants.UNAUTHORIZED_USER;
		}
		Level level;
		try {
			level = levelService.getLevel(id, id);
			
			LOG.info("Request from " + user.getFirstName() + " to access level " + level.getLevel());
		}
		catch(Exception e) {
			e.printStackTrace();
			return ErrorConstants.NOT_FOUND;
		}
		
		return new LevelResponse(level);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/level/{plev}/{lev}", produces = "application/json")
	public Response getLevel(@PathVariable("plev") String parentLev, @PathVariable("lev") String lev, @RequestHeader("authToken") String token) {
		ObUser user = userService.findByToken(token);
		Long parent, subLev;
		try {
			parent = Long.parseLong(parentLev);
			subLev = Long.parseLong(lev);
		}
		catch(Exception e) {
			return ErrorConstants.INVALID_LEVEL;
		}
		
		if(user == null || (user.getParentLevel() < parent) || (user.getParentLevel() == parent && user.getLevel() < subLev)) {
			return ErrorConstants.UNAUTHORIZED_USER;
		}
		
		Level level = null;
		try {
			level = levelService.getLevel(Long.parseLong(parentLev), Long.parseLong(lev));
			LOG.info("Request from " + user.getFirstName() + " to access level " + level.getLevel());
		}
		catch(Exception e) {
			e.printStackTrace();
			return ErrorConstants.NOT_FOUND;
		}
		
		return new LevelResponse(level);
	}
	
	
}
