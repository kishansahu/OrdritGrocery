package com.ordrit.fragment;

import com.ordrit.activity.DashboardActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment{
	
	public DashboardActivity dashboardActivity;

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		dashboardActivity=(DashboardActivity) activity;
	}
	
	abstract void setupUiComponent(); 

}
