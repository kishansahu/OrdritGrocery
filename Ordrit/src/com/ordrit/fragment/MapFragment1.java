package com.ordrit.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.MainActivity;


public class MapFragment1 extends BaseFragment {

	private View welcomeFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_map1);
		ImageView menu=(ImageView)actionBar.getCustomView().findViewById(R.id.menu1);
		homeActivity.previousTitle = "Map";
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (homeActivity.isMenuOpen) {
		    		homeActivity.hideMenu();
				}else {
					homeActivity.showMenu();
				}
			}
		});
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		welcomeFragment = inflater.inflate(R.layout.fragment_login,
				container, false);
		
	
		return welcomeFragment;
	}

}
