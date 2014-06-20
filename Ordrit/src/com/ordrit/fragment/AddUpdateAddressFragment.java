package com.ordrit.fragment;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.ordrit.R;
import com.ordrit.adapter.CityListAdapter;
import com.ordrit.adapter.StateListAdapter;
import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.State;
import com.ordrit.model.User;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class AddUpdateAddressFragment extends BaseFragment {
	private final String tag = "AddUpdateAddressFragment";
	private View addUpdateAddressFragment;
	private Button addUpdateAddressBack,btnAddUpdateAddressSaveOrUpdate;
	
	private EditText etAddUpdateAddressHomeOrApartmentName,
	/*etAddUpdateAddressStreet1,*/etAddUpdateAddressState,etAddUpdateAddressCity,
	etAddUpdateAddressZipcode;
	
	private TextView txtAddUpdateAddressHomeOrApartmentNameError,
	/*txtAddUpdateAddressStreet1Error,*/txtAddUpdateAddressStateError,
	txtAddUpdateAddressCityOrZipcodeError;
	private Address address;
	List<State> statesList;
	List<City> cityList;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		addUpdateAddressFragment = inflater.inflate(
				R.layout.fragment_add_or_update_address, container, false);
	
		return addUpdateAddressFragment;
	}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	
	super.onActivityCreated(savedInstanceState);
	String states=SharedPreferencesUtil.getStringPreferences(dashboardActivity, OrdritConstants.STATES);
	Type listOfObject = new TypeToken<List<State>>(){}.getType();
	statesList = gson.fromJson(states, listOfObject);
	String cites=SharedPreferencesUtil.getStringPreferences(dashboardActivity, OrdritConstants.STATES);
	Type listOfObject1 = new TypeToken<List<State>>(){}.getType();
	cityList = gson.fromJson(cites, listOfObject1);
	String strUser= SharedPreferencesUtil.getStringPreferences(dashboardActivity, OrdritConstants.USER);
	User user=gson.fromJson(strUser, User.class);
	address=user.getAddress();
	
	setupUiComponent();
	
}
	@Override
	void setupUiComponent() {
		addUpdateAddressBack = (Button) addUpdateAddressFragment
				.findViewById(R.id.addUpdateAddressBack);
		addUpdateAddressBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity
						.popFragment(FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
			}
		});
		btnAddUpdateAddressSaveOrUpdate = (Button) addUpdateAddressFragment
				.findViewById(R.id.btnAddUpdateAddressSaveOrUpdate);
		btnAddUpdateAddressSaveOrUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String strAddUpdateAddressHomeOrApartmentName = etAddUpdateAddressHomeOrApartmentName.getText().toString();
				final String strAddUpdateAddressState =getStateUrl(etAddUpdateAddressState.getText().toString());
				final String strAddUpdateAddressCity=etAddUpdateAddressCity.getText().toString();
				final String strAddUpdateAddressZipcode=etAddUpdateAddressZipcode.getText().toString();
				// validation if true
				

				new WebServiceProcessingTask() {
					
					@Override
					public void preExecuteTask() {
					TAG=tag;
					progressDialog=new ProgressDialog(dashboardActivity);
					}
					
					@Override
					public void postExecuteTask() {
						if (null!=address) {
						/*	etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
							etAddUpdateAddressState.setText(address.getState().getName());
							etAddUpdateAddressCity.setText(address.getCity().getName());
							etAddUpdateAddressZipcode.setText(address.getPincode());
						*/	
						}
					}
					
					@Override
					public void backgroundTask() {
					 
						List<NameValuePair> list=new ArrayList<NameValuePair>();
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_USER, OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS+"/8"));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_STREET_ADDRESS, strAddUpdateAddressHomeOrApartmentName));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_CITY, strAddUpdateAddressCity));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_STATE, strAddUpdateAddressState));
		                list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_PINCODE, strAddUpdateAddressZipcode));
						
						
						jSONString  = connection.postHttpUrlConnection(CommonUtils.getParamListJSONString(list),OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS_ADDRESS,
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
								
						try {
							address = OrditJsonParser.getMerchantAddressFromJSON(jSONString);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});
		
        //edittext
		etAddUpdateAddressHomeOrApartmentName = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressHomeOrApartmentName);
		etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
		etAddUpdateAddressState = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressState);
		etAddUpdateAddressState.setText(getStateName(address.getState().getUrl()));
		etAddUpdateAddressState.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showStateDialog();
				
			}
		});
		etAddUpdateAddressCity = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressCity);
		etAddUpdateAddressCity.setText(address.getCity().getUrl());
		etAddUpdateAddressCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showCityDialog();
				
			}
		});
		
		etAddUpdateAddressZipcode = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressZipcode);
		
		
		//error textview
		txtAddUpdateAddressHomeOrApartmentNameError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressHomeOrApartmentNameError);
			txtAddUpdateAddressStateError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressStateError);
		txtAddUpdateAddressCityOrZipcodeError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressCityOrZipcodeError);
		setAddress();
	}
	public void setAddress() {
		if (null!=address) {
			
			etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
			etAddUpdateAddressState.setText(address.getState().getName());
			etAddUpdateAddressCity.setText(address.getCity().getName());
			etAddUpdateAddressZipcode.setText(address.getPincode());
			
		}
	}

	private void showStateDialog() {
		final Dialog dialog = new Dialog(dashboardActivity);
		dialog.getWindow();
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_states_list);
        final TextView dialogTitle=(TextView)dialog.findViewById(R.id.dialogTitle);
       final ListView lv = (ListView) dialog.findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				etAddUpdateAddressState.setText(statesList.get(position).getName());
				dialog.dismiss();
				
			}
		});
		dialog.setCancelable(true);
		
		String states=SharedPreferencesUtil.getStringPreferences(dashboardActivity, OrdritConstants.STATES);
		Type listOfObject = new TypeToken<List<State>>(){}.getType();
		statesList = gson.fromJson(states, listOfObject);
		if (statesList!=null) {
			StateListAdapter adapter = new StateListAdapter(dashboardActivity, R.layout.states_list_item,statesList);
		    lv.setAdapter(adapter);	
		} 
	
		dialog.show();
	}
	private void showCityDialog() {
		final Dialog dialog = new Dialog(dashboardActivity);
		dialog.getWindow();
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_states_list);
        final TextView dialogTitle=(TextView)dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Cities");
       final ListView lv = (ListView) dialog.findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				etAddUpdateAddressCity.setText(cityList.get(position).getName());
				dialog.dismiss();
				
			}
		});
		dialog.setCancelable(true);
		
		String states=SharedPreferencesUtil.getStringPreferences(dashboardActivity, OrdritConstants.CITIES);
		Type listOfObject = new TypeToken<List<City>>(){}.getType();
		cityList = gson.fromJson(states, listOfObject);
		if (cityList!=null) {
			CityListAdapter adapter = new CityListAdapter(dashboardActivity, R.layout.states_list_item,cityList);
		    lv.setAdapter(adapter);	
		} 
	
		dialog.show();
	}
	private String getStateUrl(String name) {
		String url = null;
		for (int i = 0; i < statesList.size(); i++) {
			State states =statesList.get(i);
			if (states.getName().equals(name)) {
				url=states.getUrl();
				break;
			}
		}
		
		return url;
	}
	private String getCityUrl(String name) {
		String url = null;
		for (int i = 0; i < cityList.size(); i++) {
			City city =cityList.get(i);
			if (city.getName().equals(name)) {
				url=city.getUrl();
				break;
			}
		}
		
		return url;
	}
	private String getStateName(String url) {
		String name = null;
		for (int i = 0; i < statesList.size(); i++) {
			State states =statesList.get(i);
			if (states.getUrl().equals(url)) {
				name=states.getName();
				break;
			}
		}
		
		return url;
	}
	private String getCityName(String url) {
		String name = null;
		for (int i = 0; i < cityList.size(); i++) {
			City city =cityList.get(i);
			if (city.getUrl().equals(url)) {
				name=city.getName();
				break;
			}
		}
		
		return url;
	}
}
