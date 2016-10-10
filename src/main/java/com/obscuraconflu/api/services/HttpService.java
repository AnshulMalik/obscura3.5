package com.obscuraconflu.api.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface HttpService {
	
	public String post(String url, Map<String, String> params) throws MalformedURLException, IOException;
	
	public String get(String url, Map<String, String> params) throws MalformedURLException, IOException;

}