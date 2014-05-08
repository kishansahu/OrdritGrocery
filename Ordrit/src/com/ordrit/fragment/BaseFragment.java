package com.ordrit.fragment;

import com.ordrit.activity.HomeActivity;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;

public class BaseFragment extends Fragment{
	
	public HomeActivity homeActivity;
	public ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        homeActivity=(HomeActivity) getActivity();
        actionBar=homeActivity.getActionBar();
	}
	

}
