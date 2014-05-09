package com.ordrit.fragment;

import com.ordrit.R;
import com.ordrit.util.FragmentConstant;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuFragment extends BaseFragment {
	
	private View menuFragment;
	private Button menuAccount, menuBag, menuOrderStatus,
			menuPreviousOrder, menuCoupon, menuHelp, menuShoping;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment=inflater.inflate(R.layout.fragment_menu, container,false);
		setupUiComponent();
		return menuFragment;
	}
	@Override
	void setupUiComponent() {
		menuAccount=(Button)menuFragment.findViewById(R.id.menuAccount);
		menuAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
			}
		});
		menuBag=(Button)menuFragment.findViewById(R.id.menuBag);
		menuBag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
				MenuBagFragment menuBagFragment = new MenuBagFragment();
				dashboardActivity.commitFragment(menuBagFragment,FragmentConstant.MENU_BAG_FRAGMENT);
				
				
			}
		});
		menuOrderStatus=(Button)menuFragment.findViewById(R.id.menuOrderStatus);
		menuOrderStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuPreviousOrder=(Button)menuFragment.findViewById(R.id.menuPreviousOrder);
		menuPreviousOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuCoupon=(Button)menuFragment.findViewById(R.id.menuCoupon);
		menuCoupon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuHelp=(Button)menuFragment.findViewById(R.id.menuHelp);
		menuHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuShoping=(Button)menuFragment.findViewById(R.id.menuShoping);
		menuShoping.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
	}

}
