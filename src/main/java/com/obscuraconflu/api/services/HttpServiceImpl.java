package com.obscuraconflu.api.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpServiceImpl implements HttpService {

	private static final Logger LOG = LoggerFactory.getLogger(HttpServiceImpl.class);
	
	public String post(String url, Map<String, String> params) throws IOException {
		String paramString = paramsToString(params);
		LOG.info("Query string: " + paramString);
		URL u = new URL(url);
		byte[] postData = paramString.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		LOG.info("length: "+ Integer.toString(postDataLength));
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setDoOutput(true);
		con.setInstanceFollowRedirects(false);
		con.setRequestMethod("POST");
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		con.setUseCaches( false );
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
		   wr.write( postData );
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String input, response = "";
		while ((input = in.readLine()) != null)
			response += input;
		return response; 
		
	}
	
	public String get(String url, Map<String, String> params) throws IOException {
		String paramString = paramsToString(params);
		LOG.info("Query string: " + paramString);
		URL u = new URL(url + paramString);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setDoOutput(true);
		con.setInstanceFollowRedirects(true);
		con.setUseCaches( false );
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String input, response = "";
		while ((input = in.readLine()) != null)
			response += input;
		return response; 
		
	}

	private String paramsToString(Map<String, String> params) {
		String output = "";
		for(String key: params.keySet()) {
			String value = params.get(key);
			output = output + (key + "=" + value + "&");
		}
		return output;
	}

}
