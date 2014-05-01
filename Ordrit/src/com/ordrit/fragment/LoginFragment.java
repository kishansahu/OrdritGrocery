package com.ordrit.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.MainActivity;

public class LoginFragment extends Fragment {

	private View loginFragment;
	private MainActivity mainActivity = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity) getActivity();
		mainActivity.getActionBar().hide();
		loginFragment = inflater.inflate(R.layout.fragment_login, container,
				false);
		TextView headingText = (TextView) loginFragment
				.findViewById(R.id.login_heading);
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));

		Button loginButton = (Button) loginFragment
				.findViewById(R.id.buttonLogin);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				Fragment userAddMerchantFragment = new UserAddMerchantFragment();

				fragmentTransaction.replace(R.id.application_container,
						userAddMerchantFragment);
				fragmentTransaction.commit();

			}
		});

		return loginFragment;
	}

}
