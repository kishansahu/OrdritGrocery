package com.ordrit.fragment;


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
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.model.User;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class UpdateAccountFragment extends BaseFragment {
 
	private final String tag = "UpdateAccountFragment";
	private View updateAccountFragment;
	private Button updateAccountBack;
	private EditText etUpdateAccountFirstName, etUpdateAccountLastName,
			etUpdateAccountMobileNumber, etUpdateAccountUserEmailId;
	private TextView txtUpdateAccountFirstNameError,
			txtUpdateAccountLastNameError, txtUpdateAccountMobileNumberError,
			txtUpdateAccountUserEmailIdError;
	private User user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		updateAccountFragment = inflater.inflate(
				R.layout.fragment_update_account, container, false);
		
		return updateAccountFragment;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		 user=dashboardActivity.getUser();
		setupUiComponent();
	}

	@Override
	void setupUiComponent() {
			updateAccountBack = (Button) updateAccountFragment
				.findViewById(R.id.updateAccountBack);
		updateAccountBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity
						.popFragment(FragmentConstant.UPDATE_ACCOUNT_FRAGMENT);
			}
		});
		etUpdateAccountFirstName = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountFirstName);

		etUpdateAccountLastName = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountLastName);

		etUpdateAccountMobileNumber = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountMobileNumber);

		etUpdateAccountUserEmailId = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountUserEmailId);
		
		// error
		
		txtUpdateAccountFirstNameError=(TextView) updateAccountFragment
		.findViewById(R.id.txtUpdateAccountFirstNameError);
		
		txtUpdateAccountLastNameError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountLastNameError);
		
		txtUpdateAccountMobileNumberError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountMobileNumberError);
		txtUpdateAccountUserEmailIdError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountUserEmailIdError);
		setData();
		
	}
	private void  setData() {
		if (user!=null) {
			etUpdateAccountFirstName.setText(user.getFirstName());
			etUpdateAccountLastName.setText(user.getLastName());
			etUpdateAccountMobileNumber.setText(user.getPhoneNumber());
			etUpdateAccountUserEmailId.setText(user.getEmailId());
			
		}
	}

}
