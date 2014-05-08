/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ordrit.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.fragment.DrawerFragment;
import com.ordrit.fragment.MapDetailFragment;


public class HomeActivity extends Activity {
	Context context;
    RelativeLayout leftMenuContainerLayout; 
    Animation leftToright, rightToleft;
    View shaddow;
    public String previousTitle;
   
    public boolean isMenuOpen=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home1);
		context = this;
		shaddow=(View)findViewById(R.id.shaddow);
		shaddow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			hideMenu();
				
			}
		});
		rightToleft = AnimationUtils.loadAnimation(context,
				R.anim.right_to_left);
		rightToleft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
				shaddow.setVisibility(View.GONE);
				TextView title=(TextView)getActionBar().getCustomView().findViewById(R.id.title);
				title.setText(previousTitle);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				
			}
		}) ;                   
		leftToright = AnimationUtils.loadAnimation(context,
				R.anim.left_to_right);
		leftToright.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				shaddow.setVisibility(View.VISIBLE);
				TextView title=(TextView)getActionBar().getCustomView().findViewById(R.id.title);
				title.setText("Menu");
			}
		}) ; 
		leftMenuContainerLayout=(RelativeLayout)findViewById(R.id.leftMenuContainerLayout);
		MapDetailFragment loginFragment = new MapDetailFragment();
		commitFragment(loginFragment);
		commitMenuFragment();
		
	}

	public void commitFragment(Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();


		fragmentTransaction.replace(R.id.mainContainer, fragment);
		fragmentTransaction.commit();
	}

	public void commitMenuFragment() {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		DrawerFragment drawerFragment= new DrawerFragment();
		fragmentTransaction.add(R.id.leftMenuContainer, drawerFragment);
		fragmentTransaction.commit();
	}

public void showMenu() {
	
	leftMenuContainerLayout.setVisibility(View.VISIBLE);
	leftMenuContainerLayout.startAnimation(leftToright);
    isMenuOpen=true;
}

public void hideMenu() {
	leftMenuContainerLayout.setVisibility(View.GONE);
	leftMenuContainerLayout.startAnimation(rightToleft);
	isMenuOpen=false;
}
		
}