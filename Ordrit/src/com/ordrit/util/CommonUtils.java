package com.ordrit.util;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {

	private Context _context;

	public CommonUtils() {
		// TODO Auto-generated constructor stub
	}

	public CommonUtils(Context context) {
		this._context = context;
	}

	public static void hideSoftKeyboard(Context context, View inputView) {
		if (inputView == null)
			return;

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
	}

	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
	public static String getParamListJSONString(List<NameValuePair> paramList) {
		String paramListString = new String();
		JSONObject userObject = new JSONObject();
		try {
			for (int i = 0; i < paramList.size(); i++) {
				userObject.put(paramList.get(i).getName(), paramList.get(i).getValue());
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		paramListString = userObject.toString();

		return paramListString;
	}
}
