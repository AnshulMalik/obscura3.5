package com.obscuraconflu.api.services;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.dto.SignUpRequest;
import com.obscuraconflu.api.dto.SocialLoginRequest;
import com.obscuraconflu.api.models.ObUser;

public interface UserService {

	public ObUser findById(Long id);

	public ObUser findByEmail(String email);

	public ObUser findByToken(String token);

	public Response signup(SignUpRequest signupRequest);

	public Response signup(SocialLoginRequest socialLoginRequest);

	public boolean updateLevel(ObUser user, Long parentLevel, Long level, String url, Timestamp currentTime);

	public BigInteger getRank(BigInteger id);

	public List<ObUser> getAllUsers();

}
