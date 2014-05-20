package com.ordrit.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.User;

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
	
	public static String editUserDetailsJSONObjectString(User user){
		String userEditDetailsString = new String();
		
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_PASSWORD, user.getPassword());
			userObject.put(OrdritJsonKeys.USER_EMAIL, user.getEmailId());
			userObject.put(OrdritJsonKeys.USER_FIRSTNAME, user.getFirstName());
			userObject.put(OrdritJsonKeys.USER_LASTNAME, user.getLastName());
			userObject.put(OrdritJsonKeys.USER_LAST_LOGIN, user.getLastLoginDate());
			userObject.put(OrdritJsonKeys.USER_DATE_JOINED, user.getJoinDate());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userEditDetailsString = userObject.toString();
		
		return userEditDetailsString;
	}
	
}