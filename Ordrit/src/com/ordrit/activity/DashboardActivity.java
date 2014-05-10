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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.internal.el;
import com.ordrit.R;
import com.ordrit.fragment.MenuFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.util.FragmentConstant;


public class DashboardActivity extends Activity {
	private final String TAG="DashboardActivity";
	Context context;
    RelativeLayout leftMenuContainerLayout; 
    Button menu;
    Animation leftToright, rightToleft;
    View shaddow;
   
    public boolean isMenuOpen=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		context = this;
		shaddow=(View)findViewById(R.id.shaddow);
		shaddow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			clickMenu();
				
			}
		});
		menu=(Button)findViewById(R.id.menu);
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickMenu();
				
			}
		});
		rightToleft = AnimationUtils.loadAnimation(context,
				R.anim.right_to_left);
		rightToleft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
				shaddow.setVisibility(View.GONE);
				
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
				
			}
		}) ; 
		leftMenuContainerLayout=(RelativeLayout)findViewById(R.id.leftMenuContainerLayout);
		MapDetailFragment mapDetailFragment = new MapDetailFragment();
		commitFragment(mapDetailFragment,null);
		commitMenuFragment();
		
	}

	public void commitFragment(Fragment fragment,String tag) {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment);
        if (tag!=null) {
        	 fragmentTransaction.addToBackStack(tag);
		}
       
		fragmentTransaction.commit();
		
	}
    public void popFragment(String tag) {
    	 
    	 FragmentManager fragmentManager = getFragmentManager();
    	 fragmentManager.popBackStack (tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    			
     }
	public void commitMenuFragment() {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		MenuFragment drawerFragment= new MenuFragment();
		fragmentTransaction.add(R.id.leftMenuContainer, drawerFragment);
		fragmentTransaction.commit();
	}

public void clickMenu() {
	if (isMenuOpen) {
		leftMenuContainerLayout.setVisibility(View.GONE);
		leftMenuContainerLayout.startAnimation(rightToleft);
		isMenuOpen=false;
	}else {
		
		leftMenuContainerLayout.setVisibility(View.VISIBLE);
		leftMenuContainerLayout.startAnimation(leftToright);
	    isMenuOpen=true;
	}
	
}		
}