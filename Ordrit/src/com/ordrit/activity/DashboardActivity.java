package com.ordrit.activity;


import java.lang.reflect.Type;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ordrit.R;
import com.ordrit.adapter.NavDrawerListAdapter;
import com.ordrit.adapter.SeparatedListAdapter;
import com.ordrit.fragment.BaseFragment;
import com.ordrit.fragment.HelpFragment;
import com.ordrit.fragment.ManageUserInfoFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.fragment.MenuBagFragment;
import com.ordrit.fragment.OrderStatusFragment;
import com.ordrit.fragment.PreviousOrdersFragment;
import com.ordrit.fragment.StoreItemsCategoryFragment;
import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.NavDrawerItem;
import com.ordrit.model.State;
import com.ordrit.model.User;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.SharedPreferencesUtil;

public class DashboardActivity extends Activity {
	private Context context;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    public boolean isMenuOpen=false;
    public boolean updateListView=false;
	private SeparatedListAdapter adapter;
	private Gson gson;
	private List<State> statesList;
	private List<City> cityList;
	private User user;
	private String selectedStoreId;
	
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
			displayView(new NavDrawerItem(), 1);
			
		}
		// setting assential data
		gson= new Gson();
		String states=SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.STATES);
		Type listOfObject = new TypeToken<List<State>>(){}.getType();
		statesList = gson.fromJson(states, listOfObject);
		
		String cites=SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.CITIES);
		Type listOfObject1 = new TypeToken<List<City>>(){}.getType();
		cityList = gson.fromJson(cites, listOfObject1);
		
		String strUser= SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.USER);
	    user=gson.fromJson(strUser, User.class);
	   // setUser(user);
	
		
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
			case 1:
				fragment = new MapDetailFragment();
				break;
			case 2:
				fragment = new ManageUserInfoFragment();;
				break;
			case 3:
				fragment = new MenuBagFragment();
				break;
			case 4:
				fragment = new OrderStatusFragment();
				break;
			case 5:
				fragment = new PreviousOrdersFragment();
				break;
			case 6:
				fragment = new HelpFragment();
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

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public List<State> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<State> statesList) {
		this.statesList = statesList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public User getUser() {
		if(user==null){
			String strUser= SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.USER);
		    user=gson.fromJson(strUser, User.class);
		    	
		}
		return user;
	}

	public void setUser(User user) {
		SharedPreferencesUtil.saveStringPreferences(context, OrdritConstants.USER, gson.toJson(user));
		this.user = user;
		
		
	}

	 @Override
	public void onBackPressed() {
		
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
	 
				// set title
				alertDialogBuilder.setTitle("Ordrit");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Do you want to exit ?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
							finish();
						}
					  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
	}
	 
	 public void checkCartItems(View fragmentView){
		 UILApplication uilApplication =(UILApplication) getApplication();
		 if(uilApplication.getSelectedItemList()!=null){
		 int itemsCount= uilApplication.getSelectedItemList().size();
		 RelativeLayout cartContainer= (RelativeLayout) fragmentView.findViewById(R.id.cartContainer);
		 if(itemsCount> 0){
			cartContainer.setVisibility(View.VISIBLE);
			TextView cartItemCount= (TextView) fragmentView.findViewById(R.id.cart_item_count);
			cartItemCount.setText(""+itemsCount);
			ImageView cartLogo= (ImageView)fragmentView.findViewById(R.id.cartLogo);
			cartLogo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				commitFragment(new MenuBagFragment(), null)	;
					
				}
			});
		 }else{
			 cartContainer.setVisibility(View.GONE); 
		 }
		 }
	 }
	 @Override
		public boolean dispatchTouchEvent(MotionEvent event) {
			View currentView = getCurrentFocus();

			boolean retVal = super.dispatchTouchEvent(event);
			if (currentView instanceof EditText) {
				View cView = getCurrentFocus();
				int scrCoordinates[] = new int[2];
				cView.getLocationOnScreen(scrCoordinates);
				float xPos = event.getRawX() + cView.getLeft() - scrCoordinates[0];
				float yPos = event.getRawY() + cView.getTop() - scrCoordinates[1];

				if (event.getAction() == MotionEvent.ACTION_UP
						&& (xPos < cView.getLeft() || xPos >= cView.getRight()
								|| yPos < cView.getTop() || yPos > cView
								.getBottom())) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
							.getWindowToken(), 0);
				}
			}
			return retVal;
		}

	public String getSelectedStoreId() {
		return selectedStoreId;
	}

	public void setSelectedStoreId(String selectedStoreId) {
		this.selectedStoreId = selectedStoreId;
	} 
	
	public boolean isUserProfileIncomplete(){
		boolean isUserProfileIncomplete= false;
		Gson gson = new Gson();
		String strUser = SharedPreferencesUtil.getStringPreferences(
				context, OrdritConstants.USER);
		
	   User user = gson.fromJson(strUser, User.class);
	   String mobileNo=user.getPhoneNumber();
	   Address address=user.getAddress();
	   if (mobileNo == null || mobileNo.isEmpty() || address == null) {
		   isUserProfileIncomplete= true;
	   }
	   return isUserProfileIncomplete;
	}
	   
    public void forceUserToCompleteProfile(){
    	
		//Start Force to enter User Details
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

			// set title
			alertDialogBuilder.setTitle("Ordrit");

			// set dialog message
			alertDialogBuilder
				.setMessage("Please complete your Account details before progressing. \nAfter completion select your favourite store for shopping.")
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
						commitFragment(new ManageUserInfoFragment(),null);
						
					}
				  });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
		//End to fill user details
	
   	 
   	
    }
	 
}







