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

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.adapter.CustomDrawerAdapter;
import com.ordrit.adapter.NewAdapter;
import com.ordrit.model.DrawerItem;


public class HomeActivity extends Activity {
	Context context;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
   // private String[] mPlanetTitles;
    List<DrawerItem> groupItem;
    //ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        
  
        setGroupData();
        setChildGroupData();
        
        
        NewAdapter customDrawerAdapter=new NewAdapter(context,groupItem, childItem);
        
        mDrawerList.setAdapter(customDrawerAdapter);
      /*  if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        	mDrawerList.setIndicatorBounds(mDrawerList.getWidth()- 40, mDrawerList.getWidth());
         } else {
        	 mDrawerList.setIndicatorBounds(mDrawerList.getWidth()- 40, mDrawerList.getWidth());
         }*/
       // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
       // getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));  
       // getActionBar().setDisplayShowHomeEnabled(false) ;
      //  getActionBar().setDisplayShowTitleEnabled(false);
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_settings:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
       
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
      //  setTitle(groupItem.get(position).getTitle());
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
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

    public void setGroupData() {
    	
        groupItem=new ArrayList<DrawerItem>();
        DrawerItem drawerItem0 = new DrawerItem(getString(R.string.my_accounts), R.drawable.ic_launcher);
        groupItem.add(drawerItem0);
        DrawerItem drawerItem1 = new DrawerItem(getString(R.string.my_bag), R.drawable.ic_launcher);
        groupItem.add(drawerItem1);
        DrawerItem drawerItem2 = new DrawerItem(getString(R.string.order_status), R.drawable.ic_launcher);
        groupItem.add(drawerItem2);
        DrawerItem drawerItem3 = new DrawerItem(getString(R.string.previous_orders), R.drawable.ic_launcher);
        groupItem.add(drawerItem3);
        DrawerItem drawerItem4 = new DrawerItem(getString(R.string.coupons), R.drawable.ic_launcher);
        groupItem.add(drawerItem4);
        DrawerItem drawerItem5 = new DrawerItem(getString(R.string.help), R.drawable.ic_launcher);
        groupItem.add(drawerItem5);
        DrawerItem drawerItem6 = new DrawerItem(getString(R.string.shopping_in), R.drawable.ic_launcher);
        groupItem.add(drawerItem6);
        
    	
    	
		/*groupItem.add("TechNology");
		groupItem.add("Mobile");
		groupItem.add("Manufacturer");
		groupItem.add("Extras");*/
	}

	

	public void setChildGroupData() {
		/**
		 * Add Data For TecthNology
		 */
		ArrayList<String> child = new ArrayList<String>();
		childItem.add(child);
		childItem.add(child);
		childItem.add(child);
		childItem.add(child);
		childItem.add(child);
		childItem.add(child);
		child = new ArrayList<String>();
		child.add("Contact Us");
		child.add("About Us");
		child.add("Location");
		child.add("Root Cause");
		childItem.add(child);

		/**
		 * Add Data For Mobile
		 *//*
		child = new ArrayList<String>();
		child.add("Android");
		child.add("Window Mobile");
		child.add("iPHone");
		child.add("Blackberry");
		childItem.add(child);
		*//**
		 * Add Data For Manufacture
		 *//*
		child = new ArrayList<String>();
		child.add("HTC");
		child.add("Apple");
		child.add("Samsung");
		child.add("Nokia");
		childItem.add(child);
		*//**
		 * Add Data For Extras
		 *//*
		child = new ArrayList<String>();
		child.add("Contact Us");
		child.add("About Us");
		child.add("Location");
		child.add("Root Cause");
		childItem.add(child);*/
	}


}