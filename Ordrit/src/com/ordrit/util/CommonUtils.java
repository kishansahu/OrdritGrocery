package com.ordrit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.R;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.model.NavDrawerItem;
import com.ordrit.model.Store;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

public class CommonUtils {

	private Context _context;

	public CommonUtils() {
		// TODO Auto-generated constructor stub
	}

	public CommonUtils(Context context) {
		this._context = context;
	}

	public static void hideSoftKeyboard(Context context, View inputView) {
		if (inputView == null)
			return;

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
	}

	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
	public static String getParamListJSONString(List<NameValuePair> paramList) {
		String paramListString = new String();
		JSONObject userObject = new JSONObject();
		try {
			for (int i = 0; i < paramList.size(); i++) {
				userObject.put(paramList.get(i).getName(), paramList.get(i).getValue());
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		paramListString = userObject.toString();

		return paramListString;
	}

	public static ArrayList<NavDrawerItem> getNavDrawerItem(Context context) {
		ArrayList<NavDrawerItem> navDrawerItemList = new ArrayList<NavDrawerItem>();

		// load slide menu items
		String[] navMenuTitles = context.getResources().getStringArray(
				R.array.nav_drawer_items);

		// nav drawer icons from resources
		TypedArray navMenuIcons = context.getResources().obtainTypedArray(
				R.array.nav_drawer_icons);

		// adding nav drawer items to array
		// Home
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Find People
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Photos
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1), true, "22"));
		// Pages
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		// What's hot, We will add a counter here
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1), true, "50+"));
		navDrawerItemList.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1), true, "50+"));
		

		OrdrItdataBaseHelper ordrItdataBaseHelper=new OrdrItdataBaseHelper(context);
		List<Store> list=ordrItdataBaseHelper.getAllAddedStore();
		Iterator<Store> iterator = list.iterator();
		while (iterator.hasNext()) {
			Store store=iterator.next();
			navDrawerItemList.add(new NavDrawerItem(store.getStoreName(), navMenuIcons
					.getResourceId(6, -1)));
		}
		
		navMenuIcons.recycle();

		return navDrawerItemList;
	}
}
