package com.ordrit.activity;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.adapter.NavDrawerListAdapter;
import com.ordrit.adapter.SeparatedListAdapter;
import com.ordrit.fragment.StoreItemsCategoryFragment;
import com.ordrit.fragment.ManageUserInfoFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.fragment.MenuBagFragment;
import com.ordrit.model.NavDrawerItem;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.OrdritConstants;

public class DashboardActivity extends Activity {
	private Context context;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    public boolean isMenuOpen=false;
    public boolean updateListView=false;
	private SeparatedListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        context=this;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		updateMenu();

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
				if (updateListView) {
					updateMenu();
					updateListView=false;
				}
				
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(new NavDrawerItem(), -1);
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
			NavDrawerItem navDrawerItem=(NavDrawerItem) parent.getAdapter().getItem(position);
			displayView(navDrawerItem, position);
		}
	}




	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(NavDrawerItem navDrawerItem,int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		if (navDrawerItem.getId()==null) {
			switch (position) {
			case -1:
				fragment = new MapDetailFragment();
				break;
			case 1:
				fragment = new ManageUserInfoFragment();;
				break;
			case 2:
				fragment = new MenuBagFragment();
				break;
			default:
				break;
			}
		}else {
			fragment = new StoreItemsCategoryFragment();
			Bundle bundle=new Bundle();
		    bundle.putString(OrdritConstants.STORE_ID, navDrawerItem.getId());
		  //  Toast.makeText(context,""+navDrawerItem.getId() , 1).show();
		    fragment.setArguments(bundle);
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
	private void updateMenu() {
		
		        adapter=null;
		        mDrawerList.setAdapter(null);
				adapter =new SeparatedListAdapter(context);
				adapter.addSection("Menu", new NavDrawerListAdapter(context, CommonUtils.getNavDrawerItem(context)));
				adapter.addSection("Stores", new NavDrawerListAdapter(context, CommonUtils.getNavDrawerItemStore(context)));
				mDrawerList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				
	}
}
