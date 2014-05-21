package com.ordrit.fragment;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.model.Address;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;
import com.ordrit.util.WebServicesRawDataUtil;

public class AddUpdateAddressFragment extends BaseFragment {
	private ProgressBar progressBar;
	private final String tag = "AddUpdateAddressFragment";
	private View addUpdateAddressFragment;
	private Button addUpdateAddressBack,btnAddUpdateAddressSaveOrUpdate;
	
	private EditText etAddUpdateAddressHomeOrApartmentName,
	etAddUpdateAddressStreet1,etAddUpdateAddressStreet2,etAddUpdateAddressCity,
	etAddUpdateAddressZipcode;
	
	private TextView txtAddUpdateAddressHomeOrApartmentNameError,
	txtAddUpdateAddressStreet1Error,txtAddUpdateAddressStreet2Error,
	txtAddUpdateAddressCityOrZipcodeError;
	private Address address;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		addUpdateAddressFragment = inflater.inflate(
				R.layout.fragment_add_or_update_address, container, false);
		setupUiComponent();
		return addUpdateAddressFragment;
	}

	@Override
	void setupUiComponent() {
		progressBar= (ProgressBar) addUpdateAddressFragment
				.findViewById(R.id.progressBar);
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
				
				
				new WebServiceProcessingTask() {
					
					@Override
					public void preExecuteTask() {
					TAG=tag;
					progressBar.setVisibility(View.VISIBLE);
					}
					
					@Override
					public void postExecuteTask() {
						if (null!=address) {
							etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
							etAddUpdateAddressCity.setText(address.getCity());
							etAddUpdateAddressZipcode.setText(address.getCity());
							
						}
						progressBar.setVisibility(View.GONE);
					}
					
					@Override
					public void backgroundTask() {/*
					 
					List<NameValuePair> list=new ArrayList<NameValuePair>();
					list.add(new BasicNameValuePair(name, value));
						jSONObject  = connection.postHttpUrlConnection(comm);
								
						try {
							address = OrditJsonParser.getMerchantAddressFromJSON(jSONObject);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					*/}
				}.execute();
			}
		});
		
//edittext
		etAddUpdateAddressHomeOrApartmentName = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressHomeOrApartmentName);
		etAddUpdateAddressStreet1 = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressStreet1);
		etAddUpdateAddressStreet2 = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressStreet2);
		etAddUpdateAddressCity = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressCity);
		etAddUpdateAddressZipcode = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressZipcode);
		//error textview
		txtAddUpdateAddressHomeOrApartmentNameError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressHomeOrApartmentNameError);
		txtAddUpdateAddressStreet1Error = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressStreet1Error);
		txtAddUpdateAddressStreet2Error = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressStreet2Error);
		txtAddUpdateAddressCityOrZipcodeError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressCityOrZipcodeError);
		
	new WebServiceProcessingTask() {
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
			progressBar.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void postExecuteTask() {
				if (null!=address) {
					etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
					etAddUpdateAddressCity.setText(address.getCity());
					etAddUpdateAddressZipcode.setText(address.getCity());
					
				}
				progressBar.setVisibility(View.GONE);
			}
			
			@Override
			public void backgroundTask() {
			
				jSONObject = connection.getHttpUrlConnection(
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS_ADDRESS+"/8",
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
				try {
					address = OrditJsonParser.getMerchantAddressFromJSON(jSONObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.execute();
	}

}
