package com.ordrit.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ordrit.R;
import com.ordrit.util.FragmentConstant;

public class ManageUserInfoFragment extends BaseFragment {

	private View manageUserInfoFragment;
	private Button manageAccountMenu,btnManageAccountLogout;
	private LinearLayout containerLinkManageAccount,containerLinkManageAddress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		manageUserInfoFragment = inflater.inflate(
				R.layout.fragment_manage_users_info, container, false);
		setupUiComponent();
		return manageUserInfoFragment;
	}

	@Override
	void setupUiComponent() {

		manageAccountMenu = (Button) manageUserInfoFragment
				.findViewById(R.id.manageAccountMenu);
		manageAccountMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
		
		btnManageAccountLogout = (Button) manageUserInfoFragment
				.findViewById(R.id.btnManageAccountLogout);
		btnManageAccountLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
			}
		});
		containerLinkManageAccount =(LinearLayout) manageUserInfoFragment
				.findViewById(R.id.containerLinkManageAccount);
		containerLinkManageAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateAccountFragment updateAccountFragment = new UpdateAccountFragment();
				dashboardActivity.commitFragment(updateAccountFragment,FragmentConstant.UPDATE_ACCOUNT_FRAGMENT);
			}
		});
		containerLinkManageAddress =(LinearLayout) manageUserInfoFragment
				.findViewById(R.id.containerLinkManageAddress);
		containerLinkManageAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddUpdateAddressFragment addUpdateAddressFragment = new AddUpdateAddressFragment();
				dashboardActivity.commitFragment(addUpdateAddressFragment,FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
			}
		});
	}

}