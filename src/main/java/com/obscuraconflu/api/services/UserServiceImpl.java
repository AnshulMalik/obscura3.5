package com.obscuraconflu.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obscuraconflu.api.dao.UserDao;
import com.obscuraconflu.api.models.ObUser;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public ObUser findById(Long id) {
		return userDao.findById(id);
	}

}
