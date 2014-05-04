package com.ordrit.fragment;

import com.ordrit.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawerFragment extends Fragment {
	
	View drawerFragmentView;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		drawerFragmentView=inflater.inflate(R.layout.fragment_drawer, container);
		return drawerFragmentView;
	}

}
