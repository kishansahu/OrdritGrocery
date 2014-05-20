package com.ordrit.util;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public abstract class WebServiceProcessingTask extends AsyncTask<Void, Void, Void>{

	protected JSONObject jSONObject=null;
	protected ServerConnection connection;
	protected String TAG="WebServiceProcessingTask";
	
	@Override
	protected void onPreExecute() {
		preExecuteTask();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		postExecuteTask();
		super.onPostExecute(result);
	}

	@Override
	protected Void doInBackground(Void... params) {
	    connection = new ServerConnection();
		backgroundTask();
	//	Log.e(TAG, ""+jSONObject);
		return null;
	}
public abstract void preExecuteTask();
public abstract void postExecuteTask();
public abstract void backgroundTask();
}
