package com.obscuraconflu.api.services;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obscuraconflu.api.constant.ErrorConstants;
import com.obscuraconflu.api.controllers.UserController;
import com.obscuraconflu.api.dao.UserDao;
import com.obscuraconflu.api.dto.LoginRequestForm;
import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.dto.LoginResponse;
import com.obscuraconflu.api.dto.SignUpRequest;
import com.obscuraconflu.api.dto.SocialLoginRequest;
import com.obscuraconflu.api.models.ObUser;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@Override
	public ObUser findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public ObUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public ObUser findByToken(String token) {
		return userDao.findByToken(token);
	}

	@Override
	public Response signup(SocialLoginRequest socialLoginRequest) {
		if (userDao.findByEmail(socialLoginRequest.getEmail()) != null)
			return ErrorConstants.USERNAME_ALREADY_EXIST;

		try {
			// TODO: Encrypt password before saving user
			// signupRequest.setPassword(signupRequest.getPassword());
			ObUser user = new ObUser(socialLoginRequest, generateLoginToken(socialLoginRequest.getEmail()));

			userDao.save(user);

			return new LoginResponse(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorConstants.INTERNAL_SERVER_ERROR;
		}

	}

	@Override
	public Response signup(SignUpRequest signupRequest) {
		if (userDao.findByEmail(signupRequest.getEmail()) != null)
			return ErrorConstants.USERNAME_ALREADY_EXIST;

		try {
			// TODO: Encrypt password before saving user
			// signupRequest.setPassword(signupRequest.getPassword());
			ObUser user = new ObUser(signupRequest, generateLoginToken(signupRequest.getEmail()));

			userDao.save(user);

			return new LoginResponse(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorConstants.INTERNAL_SERVER_ERROR;
		}

	}

	private String generateLoginToken(String email) {
		String token = UUID.randomUUID().toString() + "-" + email + "-" + Calendar.getInstance().getTimeInMillis();
		return token;
	}

	@Override
	public boolean updateLevel(ObUser user, Long parentLevel, Long level) {
		user.setParentLevel(parentLevel);
		user.setLevel(level);
		try {
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public BigInteger getRank(BigInteger id) {
		List<BigInteger> ranks = userDao.getRank(id);
		if (ranks == null || ranks.isEmpty()) {
			LOG.info("Rank is null");
			return new BigInteger("-1");
		}
		BigInteger rank = new BigInteger("-1");
		
		for (BigInteger rankS : ranks) {
			if(rankS != null) {
				return rankS;
			}
		}
		return rank;
	}

}
