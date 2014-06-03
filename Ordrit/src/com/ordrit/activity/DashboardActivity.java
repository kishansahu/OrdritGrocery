package com.ordrit.activity;


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ordrit.R;
import com.ordrit.adapter.NavDrawerListAdapter;
import com.ordrit.fragment.CatogeryFragment;
import com.ordrit.fragment.ManageUserInfoFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.fragment.MenuBagFragment;
import com.ordrit.model.NavDrawerItem;
import com.ordrit.util.CommonUtils;

public class DashboardActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	 public boolean isMenuOpen=false;
	
	

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		navDrawerItems = CommonUtils.getNavDrawerItem(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

	

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		ActionBar actionBar =getActionBar();
		if (actionBar!=null) {
			getActionBar().hide();
		}
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				isMenuOpen=false;
			}

			public void onDrawerOpened(View drawerView) {
				isMenuOpen=true;
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(-1);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}




	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case -1:
			fragment = new MapDetailFragment();
			break;
		case 0:
			fragment = new ManageUserInfoFragment();;
			break;
		case 1:
			fragment = new MenuBagFragment();
			break;
		case 2:
			fragment =  new CatogeryFragment();;
			break;
	
		

		default:
			break;
		}

		if (fragment != null) {
			commitFragment(fragment, null);
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
			
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

public void commitFragment(Fragment fragment,String tag) {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        if (tag!=null) {
        	 fragmentTransaction.addToBackStack(tag);
		}
       
		fragmentTransaction.commit();
		
	}
public void popFragment(String tag) {
	 
	 FragmentManager fragmentManager = getFragmentManager();
	 fragmentManager.popBackStack (tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			
}
	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	public void clickMenu() {
		if (isMenuOpen) {
			mDrawerLayout.closeDrawer(mDrawerList);
			
		}else {
			mDrawerLayout.openDrawer(mDrawerList);
		    isMenuOpen=true;
		}
		
	}
}
