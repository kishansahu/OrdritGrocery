package com.ordrit.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.adapter.MenuBagAdapter;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.model.Item;
import com.ordrit.model.Items;
import com.ordrit.model.SelectedItem;
import com.ordrit.model.User;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;
import com.ordrit.util.WebServicesRawDataUtil;

public class MenuBagFragment extends BaseFragment {

	private View menuFragment;
	private Button back, menuBagCheckout;
	private ListView menuBagListView;
	private TextView textMerchantName, textItemTotal;
	private UILApplication uilApplication;
	private List<SelectedItem> selectedItemList;
	boolean status;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment = inflater.inflate(R.layout.fragment_menu_bag, container,
				false);
		uilApplication = (UILApplication) dashboardActivity.getApplication();
		selectedItemList = uilApplication.getSelectedItemList();
		setupUiComponent();
		return menuFragment;
	}

	@Override
	void setupUiComponent() {

		MenuBagAdapter menuBagAdapter = new MenuBagAdapter(dashboardActivity,
				R.layout.item_bag, selectedItemList);
		menuBagListView = (ListView) menuFragment
				.findViewById(R.id.menuBagListView);
		menuBagListView.setAdapter(menuBagAdapter);
		back = (Button) menuFragment.findViewById(R.id.back);
		boolean showBack=false;
		try {
			showBack=getArguments().getBoolean(OrdritConstants.SHOW_BACK_BUTTON);
		} catch (Exception e) {
			String s=e.toString();
			// TODO: handle exception
		}
		if (showBack) {
			back.setBackgroundResource(R.drawable.ic_action_previous_item);
			LinearLayout.LayoutParams fieldparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0);
			fieldparams.gravity=Gravity.CENTER;
			back.setLayoutParams(fieldparams);
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dashboardActivity
							.popFragment(FragmentConstant.MENU_BAG_FRAGMENT);
				}
			});
		}else {
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dashboardActivity.clickMenu();
				}
			});
		}
		
		menuBagCheckout = (Button) menuFragment
				.findViewById(R.id.menuBagCheckout);
		menuBagCheckout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				List<Items> orderedItemsList = new ArrayList<Items>();
				Log.e("selectedItemList", selectedItemList.toString());
				// todo

				Iterator<SelectedItem> iterator = selectedItemList.iterator();
				while (iterator.hasNext()) {
					SelectedItem selectedItem = iterator.next();
					Item item = selectedItem.getItem();
					Items orderedItem = new Items();
					orderedItem.setItem("/inventory_items/" + item.getId());
					orderedItem.setQuantity(selectedItem.getQuantity());
					orderedItemsList.add(orderedItem);
				}
				Gson gson = new Gson();
				String orderedItemsListString =gson.toJson(orderedItemsList);
				String strUser = SharedPreferencesUtil.getStringPreferences(
						getActivity(), OrdritConstants.USER);
				
				User user = gson.fromJson(strUser, User.class);
			String g=	WebServicesRawDataUtil.placeOrderJSONObjectString(user, uilApplication, orderedItemsListString);
				/*PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
				placeOrderDTO.setCustomer("/" + OrdritConstants.USERS + "/"
						+ user.getId());
				placeOrderDTO.setCustomer_address("/"
						+ OrdritConstants.USERS_ADDRESS + "/"
						+ user.getAddress().getId());
				placeOrderDTO.setCustomer("/" + OrdritConstants.STORES + "/"
						+ uilApplication.getStoreId());
				placeOrderDTO.setItems(orderedItemsList);*/
			placeOrder(g);
				
			}
		});
		textMerchantName = (TextView) menuFragment
				.findViewById(R.id.textMerchantName);
		OrdrItdataBaseHelper ordrItdataBaseHelper = new OrdrItdataBaseHelper(
				dashboardActivity);
		textMerchantName.setText(ordrItdataBaseHelper
				.getStoreName(uilApplication.getStoreId()));
		textItemTotal = (TextView) menuFragment
				.findViewById(R.id.textItemTotal);
		textItemTotal.setText(getTotalPrice());

	}

	private String getTotalPrice() {
		float total = 0;
		Iterator<SelectedItem> iterator = selectedItemList.iterator();
		while (iterator.hasNext()) {
			SelectedItem selectedItem = iterator.next();
			final Item item = selectedItem.getItem();
			float totalprice = Float.parseFloat(item.getPricePerUnit());
			totalprice = totalprice
					* (Integer.parseInt(selectedItem.getQuantity()));
			total = total + totalprice;
		}
		return String.valueOf(total);

	}
	
	private void placeOrder(final String jsonString) {
	 
		new WebServiceProcessingTask() {
			
			@Override
			public void preExecuteTask() {
			progressDialog=new ProgressDialog(getActivity());
			
			}
			
			@Override
			public void postExecuteTask() {
				if(status= true){
					
				}
			    
			}
			
			@Override
			public void backgroundTask() {
			
				jSONString = connection.postHttpUrlConnection(jsonString,
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.ORDERS,
						SharedPreferencesUtil.getStringPreferences(
								getActivity(), OrdritJsonKeys.TAG_TOKEN));
				if(jSONString.contains("status")){
					status= true;
				}
			}
		}.execute();
		
	}
}
