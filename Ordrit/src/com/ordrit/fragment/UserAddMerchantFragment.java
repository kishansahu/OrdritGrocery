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

public class UserAddMerchantFragment extends Fragment {

	private View loginFragment;
	private MainActivity mainActivity = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity) getActivity();
		mainActivity.getActionBar().show();
		// TODO
		
		loginFragment = inflater.inflate(R.layout.fragment_add_merchant, container,
				false);
		
		return loginFragment;
	}

}
