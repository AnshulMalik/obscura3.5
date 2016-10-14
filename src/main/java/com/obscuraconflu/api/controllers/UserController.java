package com.obscuraconflu.api.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.obscuraconflu.api.dto.LeaderboardResponse;
import com.obscuraconflu.api.dto.LeaderboardUser;
import com.obscuraconflu.api.dto.LevelResponse;
import com.obscuraconflu.api.dto.LoginRequestForm;
import com.obscuraconflu.api.dto.LoginResponse;
import com.obscuraconflu.api.dto.Response;
import com.obscuraconflu.api.dto.SignUpRequest;
import com.obscuraconflu.api.dto.SocialLoginRequest;
import com.obscuraconflu.api.dto.SubmitAnswerRequest;
import com.obscuraconflu.api.dto.TestRequest;
import com.obscuraconflu.api.models.Level;
import com.obscuraconflu.api.models.Notification;
import com.obscuraconflu.api.models.ObUser;
import com.obscuraconflu.api.services.HttpService;
import com.obscuraconflu.api.services.LevelService;
import com.obscuraconflu.api.services.UserService;
import com.obscuraconflu.api.constant.ErrorConstants;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	HttpService httpService;
	
	@Autowired
	LevelService levelService;
	
	private SimpMessagingTemplate template;
	
	@Autowired
	public UserController(SimpMessagingTemplate temp) {
		this.template = temp;
	}
	
	// Route for form login
	@RequestMapping(method = RequestMethod.POST, value = "/api/flogin", produces = "application/json")
	public Response flogin(@RequestBody LoginRequestForm loginRequestForm) {
		if (loginRequestForm == null || loginRequestForm.getEmail().length() == 0
				|| loginRequestForm.getPassword().length() == 0) {
			return ErrorConstants.INVALID_REQUEST;
		}
		ObUser user = userService.findByEmail(loginRequestForm.getEmail());
		if (user == null || !user.getPassword().equals(loginRequestForm.getPassword())) {
			LOG.warn(user.getFirstName() + " invalid password attempt.");
			return ErrorConstants.INVALID_EMAIL_OR_PASSWORD;
		} 
		else if(!user.getSignupType().equals("FORM")) {
			// Third party user can't login with password
			return ErrorConstants.LOGIN_WITH_SOCIAL_ACCOUNT;
		}
		else {

			return new LoginResponse(user);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/socialLogin", produces = "application/json")
	public Response socialLogin(@RequestBody SocialLoginRequest socialLoginRequest) {
		if(socialLoginRequest == null)
			return ErrorConstants.INVALID_REQUEST;
		
		if(socialLoginRequest.getAccessToken().length() == 0 || 
				socialLoginRequest.getEmail().length() == 0 ||
				socialLoginRequest.getUid().length() == 0 ||
				socialLoginRequest.getName().length() == 0 ||
				socialLoginRequest.getType() > 1) 
			return ErrorConstants.INVALID_REQUEST;
		
		int type = socialLoginRequest.getType();
		String newEmail = "";
		if(type == 0) {
			// Facebook login
			newEmail = getEmailFromFacebookToken(socialLoginRequest.getAccessToken());
		}
		else {
			newEmail = getEmailFromGoogleToken(socialLoginRequest.getAccessToken());
		}
		if(newEmail.equals(socialLoginRequest.getEmail())) {
			ObUser user = userService.findByEmail(newEmail);
			if(user == null) {
				LOG.info(socialLoginRequest.getName() + " signed up with facebook/google");
				return userService.signup(socialLoginRequest);
			}
			else {
				LOG.info(user.getFirstName() + " logged in with facebook/google");
				return new LoginResponse(user);
			}
		}
		return new Response();
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/signup", produces = "application/json")
	public Response signup(@RequestBody SignUpRequest signupRequest) {
		if(signupRequest == null || signupRequest.getEmail() == null || 
				signupRequest.getFirstName() == null || signupRequest.getPassword() == null || 
				signupRequest.getSignupType() == null) {	
			return ErrorConstants.INVALID_REQUEST;
		}
		switch(signupRequest.getSignupType()) {
			case "GOOGLE" :
				if(!getEmailFromGoogleToken(signupRequest.getAccessToken()).equals(signupRequest.getEmail())) {
					LOG.warn("Invalid google signup request from " + signupRequest.getEmail());
					return ErrorConstants.INVALID_TOKEN;
				}
				break;
			case "FACEBOOK":
				if(!getEmailFromFacebookToken(signupRequest.getAccessToken()).equals(signupRequest.getEmail())) {
					LOG.warn("Invalid face signup request from " + signupRequest.getEmail());
					return ErrorConstants.INVALID_TOKEN;
				}
				break;
			case "FORM":
				break;
			default:
				return ErrorConstants.INVALID_REQUEST;
		}
		LOG.info("Social signup request from " + signupRequest.getEmail());
		return userService.signup(signupRequest);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/leaderboard", produces = "application/json")
	public LeaderboardResponse test() {
		List<LeaderboardUser> leaderboard = new ArrayList<LeaderboardUser>();
		List<ObUser> backendUsers = userService.getAllUsers();
		Long i = 0L;
		for(ObUser user: backendUsers) {
			LeaderboardUser leaderBoardUser = new LeaderboardUser();
			
			leaderBoardUser.setLevel(user.getParentLevel());
			if(user.getLastName() != null)
				leaderBoardUser.setName(user.getFirstName() + " " + user.getLastName());
			else
				leaderBoardUser.setName(user.getFirstName());
			leaderBoardUser.setRank(i);
			i++;
			leaderboard.add(leaderBoardUser);
			
		}
		return new LeaderboardResponse(leaderboard);	
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/submitAnswer", produces = "application/json")
	public Response submitAnswer(@RequestBody SubmitAnswerRequest submitAnswerRequest, HttpServletRequest request) {
		if(submitAnswerRequest == null) {
			return ErrorConstants.INVALID_REQUEST;
		}
		LOG.info("Answer submit request from " + submitAnswerRequest.getToken() + " " + submitAnswerRequest.getUrl());
		
		String url = submitAnswerRequest.getUrl();
		String answer = levelService.getAnswerByUrl(url);
		String[] answers = answer.split("\\s+");
		
//		LOG.info("Sending answer: " + answer + " to /topic/greetings");
//		template.convertAndSend("/topic/greetings", answer);
		int flag = 0;
		
		LOG.info("Request ip: " + request.getRemoteAddr() + " forwarded for: " + request.getHeader("X-Forwarded-For") + " answer: " + submitAnswerRequest.getAnswer());
		if(url.equals("6")) {
			if(submitAnswerRequest.getAnswer().equals(request.getHeader("X-Forwarded-For")) || submitAnswerRequest.getAnswer().equals(request.getRemoteAddr())) {
				ObUser user = userService.findByToken(submitAnswerRequest.getToken());
				Level cur = levelService.getLevelByUrl(url);
				Level next = levelService.getLevelById(cur.getNextLevelId());
				
				if((user.getParentLevel() < next.getParentLevel()) || ((user.getParentLevel() == next.getParentLevel()) && (user.getLevel() < next.getLevel()))) {
					userService.updateLevel(user, next.getParentLevel(), next.getLevel(), next.getUrl());
				}
			
				return new LevelResponse(next);
			}
			
		}
		for(String answerFromArray: answers) {
			if(answerFromArray.equals(submitAnswerRequest.getAnswer())) {
				ObUser user = userService.findByToken(submitAnswerRequest.getToken());
				Level cur = levelService.getLevelByUrl(url);
				Level next = levelService.getLevelById(cur.getNextLevelId());
				
				if((user.getParentLevel() < next.getParentLevel()) || ((user.getParentLevel() == next.getParentLevel()) && (user.getLevel() < next.getLevel()))) {
					userService.updateLevel(user, next.getParentLevel(), next.getLevel(), next.getUrl());
				}
			
				return new LevelResponse(next);
			}
		}
		
		return ErrorConstants.WRONG_ANSWER;
		
	}


	private String getEmailFromGoogleToken(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		try {
			String googleResponse = httpService.get("https://www.googleapis.com/plus/v1/people/me?", params);

			JSONObject gResp = new JSONObject(googleResponse);
			JSONArray array = gResp.getJSONArray("emails");
			
			return array.getJSONObject(0).getString("value");
			    

		} catch (IOException e) {
			LOG.info("Something went wrong with captcha verification");
			e.printStackTrace();
			
		}

		return null;
	}
	
	private String getEmailFromFacebookToken(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		params.put("fields", "email");
		try {
			String facebookResponse = httpService.get("https://graph.facebook.com/v2.7/me?", params);

			JSONObject fResp = new JSONObject(facebookResponse);
			if (fResp.get("email") == null) {
				LOG.info("Invalid token");
				return null;
			} else {
				LOG.info("Token is valid returning " + (String)fResp.get("email"));
				
				return (String)fResp.get("email");
			}

		} catch (IOException e) {
			LOG.info("Something went wrong with captcha verification");
			e.printStackTrace();
			
		}

		return null;
	}
}
