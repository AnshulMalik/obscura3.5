package com.obscuraconflu.api.constant;
import com.obscuraconflu.api.dto.Response;

public class ErrorConstants {

	/**
	 * Accounts 5XX code initilization
	 */
	public static final Response INTERNAL_SERVER_ERROR = new Response(500, "Internal Server Error");
	public static final Response USERNAME_EMPTY = new Response(510, "Username can't be empty");
	public static final Response USERNAME_INVALID = new Response(510, "Entred username is not valid");
	public static final Response USERNAME_ALREADY_EXIST = new Response(510,
			"Sorry, that user already exists!");
	public static final Response INVALID_EMAIL_OR_PASSWORD = new Response(510, "Invalid email or password"); 

	public static final Response CANNT_BAN_UNVERIFIED_USER = new Response(520, "We can not ban Unverified User");
	public static final Response USER_WITH_UNKNOWN_STATUS = new Response(520, "User status is not known!");
	public static final Response USER_ALREADY_VERIFIED = new Response(520, "User is already Verified");

	public static final Response DEVICE_NOT_VALID = new Response(401, "Device not Valid");
	public static final Response USER_BANNED = new Response(403, "User is banned");
	public static final Response NOT_FOUND = new Response(404, "NOT FOUND");
	public static final Response RETRY_ATTEMPT_EXCEEDED = new Response(510,
			"Retry attempt to create username reached to threashold");
	public static final Response GENERATED_EMPTY_USERNAME = new Response(510, "Could not create username");
	public static final Response UNAUTHORIZED_USER = new Response(403,
			"Forbidden!, User is not authorized to perform this action");

	public static final Response INVALID_REQUEST = new Response(400, "BAD REQUEST/ INVALID REQUEST PARAMETERS");
	public static final Response INVALID_TOKEN = new Response(510, "Invalid token");
	public static final Response EMAIL_FACEBOOKID_NOT_FOUND = new Response(512, "Email or FacebookId not found");
	public static final Response WRONG_ANSWER = new Response(401, "Wrong answer");
	public static final Response LOGIN_WITH_SOCIAL_ACCOUNT = new Response(510, "Please login with social account instead"); 
	public static final Response INVALID_LEVEL = new Response(400, "Invalid level request");
	public static final Response GAME_NOT_STARTED = new Response(403, "The game has not started yet");
}
