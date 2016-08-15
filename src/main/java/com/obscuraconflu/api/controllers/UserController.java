package com.obscuraconflu.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.models.ObUser;
import com.obscuraconflu.api.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/users", produces = "application/json")
	public Response getUsers() {
		ObUser u = userService.findById(3L);
		return new Response(200, u.getEmail());
	}
}
