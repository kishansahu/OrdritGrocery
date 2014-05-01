package com.ordrit.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.MainActivity;


public class WelcomeFragment extends Fragment {

	private View welcomeFragment;
	private MainActivity mainActivity = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity) getActivity();
		mainActivity.getActionBar().hide();
		welcomeFragment = inflater.inflate(R.layout.fragment_welcome,
				container, false);
		TextView headingText=(TextView)welcomeFragment.findViewById(R.id.welcome_heading);
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));
		TextView subHeadingText=(TextView)welcomeFragment.findViewById(R.id.welcome_sub_heading);
		subHeadingText.setText(Html.fromHtml(getString(R.string.grocery_sub_msg)));
		Button startButton=(Button)welcomeFragment.findViewById(R.id.buttonGetStarted);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
				Animation anim = AnimationUtils.loadAnimation(
						mainActivity, R.anim.bottom_to_top);
				vi.startAnimation(anim);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				Fragment loginFragment = new LoginFragment();
				
				fragmentTransaction.replace(R.id.application_container,
						loginFragment);
				fragmentTransaction.commit();
				
			}
		});
	
		return welcomeFragment;
	}



}
