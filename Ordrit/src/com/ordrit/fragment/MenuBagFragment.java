package com.ordrit.fragment;

import com.ordrit.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuBagFragment extends BaseFragment {
	
	private View menuFragment;
	private Button menuBagBack,menuBagCheckout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment=inflater.inflate(R.layout.fragment_menu_bag, container,false);
		return menuFragment;
	}
	@Override
	void setupUiComponent() {
		
		menuBagBack=(Button)menuFragment.findViewById(R.id.menuBagBack); 
		menuBagBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dashboardActivity.onBackPressed();
			}
		});
		menuBagCheckout=(Button)menuFragment.findViewById(R.id.menuBagBack); 
		menuBagCheckout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
	}

}
