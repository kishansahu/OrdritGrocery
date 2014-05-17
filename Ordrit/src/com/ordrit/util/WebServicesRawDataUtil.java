package com.ordrit.util;

import org.json.JSONException;
import org.json.JSONObject;

public class WebServicesRawDataUtil {

	// Getting a User’s Authentication Token

	public static String getUsersAuthenticationTokenJSONObjectString(String email, String password) {
		String userCredentialsString = new String();
		email="kishansahu87@gmail.com";
		password="kishansahu";
		
		
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_PASSWORD, password);
			userObject.put(OrdritJsonKeys.USER_EMAIL, email);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userCredentialsString = userObject.toString();
		return userCredentialsString;
	}
	
}