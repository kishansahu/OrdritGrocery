package com.ordrit.fragment;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.ordrit.activity.DashboardActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment{
	
	public DashboardActivity dashboardActivity;
	protected Gson gson;

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		dashboardActivity=(DashboardActivity) activity;
		gson=new Gson();
		
	}
	
	abstract void setupUiComponent(); 

}
