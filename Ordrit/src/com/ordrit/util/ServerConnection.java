package com.ordrit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerConnection {
	// http://localhost:8080/RESTfulExample/json/product/post

	public JSONObject getHttpUrlConnection(String requestUrl,String token)
			{
		JSONObject responseObject = null; 
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(requestUrl);
		get.setHeader("Authorization", "Token "+token );
	     try {
		HttpResponse response = httpClient.execute(get);
     	responseObject = new JSONObject(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseObject;
	}

	/**
	 * Method takes the data for post call and the request url.
	 * 
	 * @param postInput
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject postHttpUrlConnection(String postInput, String requestUrl) {
		JSONObject responseObject = null; 
		// Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(requestUrl);
  
        // Making HTTP Request
        try {
        	 httpPost.setEntity(new StringEntity(postInput));
        	 httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            responseObject = new JSONObject(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return responseObject;
  }
	public JSONObject postHttpUrlConnection(String postInput, String requestUrl,String token) {
		JSONObject responseObject = null; 
		// Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(requestUrl);
  
        // Making HTTP Request
        try {
        	 httpPost.setEntity(new StringEntity(postInput));
        	 httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/json");
             httpPost.setHeader("Authorization", "Token "+token );
      	   
            HttpResponse response = httpClient.execute(httpPost);
            responseObject = new JSONObject(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
