package com.ordrit.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ordrit.R;
import com.ordrit.util.FragmentConstant;

public class UpdateAccountFragment extends BaseFragment {

	private View updateAccountFragment;
	private Button updateAccountBack;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		updateAccountFragment = inflater.inflate(
				R.layout.fragment_update_account, container, false);
		setupUiComponent();
		return updateAccountFragment;
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
		
	}

}
