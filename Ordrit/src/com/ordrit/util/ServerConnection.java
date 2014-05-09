package com.ordrit.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerConnection {
	// http://localhost:8080/RESTfulExample/json/product/post

	public HttpURLConnection getHttpUrlConnection(String requestUrl)
			throws IOException {
		URL url = new URL(requestUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		return con;
	}

	/**
	 * Method takes the data for post call and the request url.
	 * 
	 * @param postInput
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject postHttpUrlConnection(String postInput, String requestUrl, String token) {

		JSONObject responseObject = null;
		InputStream in = null;
		HttpURLConnection conn = null;
		String jsonString = null;
		try {
			URL url = new URL(requestUrl);

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			if(null!= token){
				conn.setRequestProperty("Authorization", "Token token=" + token);
			}

			
			String input = postInput;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			in = new BufferedInputStream(conn.getInputStream());
			jsonString = getStringFromInputStream(in);
			System.out.println("JSON Response : " + jsonString);
			responseObject = new JSONObject(jsonString);

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (RuntimeException e) {
			jsonString = "{\"message\":\"Internal server error\",\"statusCode\":\"err001\",\"statustext\":\""+e.toString()+"\"}";
			try {
				responseObject = new JSONObject(jsonString);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseObject;

	}

	/**
	 * Method returns String from input stream.
	 * 
	 * @param is
	 * @return jsonString
	 */
	public String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
