package com.ordrit.fragment;


import org.json.JSONException;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class AddUpdateAddressFragment extends BaseFragment {

	private final String tag = "AddUpdateAddressFragment";
	private View addUpdateAddressFragment;
	private Button addUpdateAddressBack;
	
	private EditText etAddUpdateAddressHomeOrApartmentName,
	etAddUpdateAddressStreet1,etAddUpdateAddressStreet2,etAddUpdateAddressCity,
	etAddUpdateAddressZipcode;
	
	private TextView txtAddUpdateAddressHomeOrApartmentNameError,
	txtAddUpdateAddressStreet1Error,txtAddUpdateAddressStreet2Error,
	txtAddUpdateAddressCityOrZipcodeError;
	
	
	

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

		addUpdateAddressBack = (Button) addUpdateAddressFragment
				.findViewById(R.id.addUpdateAddressBack);
		addUpdateAddressBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity
						.popFragment(FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
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
			
			}
			
			@Override
			public void postExecuteTask() {
				
				
			}
			
			@Override
			public void backgroundTask() {
			
				jSONObject = connection.getHttpUrlConnection(
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS_ADDRESS+"/8",
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
				
			}
		}.execute();
	}

}
