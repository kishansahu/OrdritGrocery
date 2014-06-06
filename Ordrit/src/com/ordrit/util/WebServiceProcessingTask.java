package com.ordrit.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

public abstract class WebServiceProcessingTask extends AsyncTask<Void, Void, Void>{

	protected String jSONString=null;
	protected ServerConnection connection;
	protected String TAG="WebServiceProcessingTask";
	protected ProgressDialog progressDialog;
	
	
	@Override
	protected void onPreExecute() {
		preExecuteTask();
		if (progressDialog!=null) {
			progressDialog.show();
		}
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		postExecuteTask();
		if (progressDialog!=null) {
			progressDialog.dismiss();
		}
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
