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


public class MapFragment extends BaseFragment {

	private View welcomeFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_map);
		ImageView menu=(ImageView)actionBar.getCustomView().findViewById(R.id.menu);
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
	
		welcomeFragment = inflater.inflate(R.layout.fragment_welcome,
				container, false);
		TextView headingText=(TextView)welcomeFragment.findViewById(R.id.welcome_heading);
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));
		TextView subHeadingText=(TextView)welcomeFragment.findViewById(R.id.welcome_sub_heading);
		subHeadingText.setText(Html.fromHtml(getString(R.string.grocery_sub_msg)));
		Button startButton=(Button)welcomeFragment.findViewById(R.id.buttonGetStarted);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MapFragment1 mapFragment = new MapFragment1();
				homeActivity.commitFragment(mapFragment);
				
			}
		});
	
		return welcomeFragment;
	}

}
