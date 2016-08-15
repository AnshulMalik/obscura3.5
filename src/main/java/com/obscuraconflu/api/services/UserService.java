package com.obscuraconflu.api.services;

import com.obscuraconflu.api.models.ObUser;

public interface UserService {
	public ObUser findById(Long id);
}
