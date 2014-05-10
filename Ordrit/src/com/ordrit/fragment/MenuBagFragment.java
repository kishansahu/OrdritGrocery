package com.ordrit.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.adapter.MenuBagAdapter;
import com.ordrit.model.MenuBagItem;
import com.ordrit.util.FragmentConstant;

public class MenuBagFragment extends BaseFragment {
	
	private View menuFragment;
	private Button menuBagBack,menuBagCheckout;
    private ListView menuBagListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment=inflater.inflate(R.layout.fragment_menu_bag, container,false);
		setupUiComponent();
		return menuFragment;
	}
	@Override
	void setupUiComponent() {
		List<MenuBagItem>	menuBagItemList= new ArrayList<MenuBagItem>();
		for (int i = 0; i < 10; i++) {
			MenuBagItem menuBagItem=new MenuBagItem(null, i);
			menuBagItemList.add(menuBagItem);
		}
		MenuBagAdapter menuBagAdapter= new MenuBagAdapter(dashboardActivity, R.layout.item_bag, menuBagItemList);
		menuBagListView=(ListView)menuFragment.findViewById(R.id.menuBagListView);
		menuBagListView.setAdapter(menuBagAdapter);
		menuBagBack=(Button)menuFragment.findViewById(R.id.menuBagBack); 
		menuBagBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.MENU_BAG_FRAGMENT);
			}
		});
		menuBagCheckout=(Button)menuFragment.findViewById(R.id.menuBagCheckout); 
		menuBagCheckout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MenuBagFragment menuBagFragment = new MenuBagFragment();
				dashboardActivity.commitFragment(menuBagFragment,FragmentConstant.MENU_BAG_FRAGMENT);
		
			}
		});
	}

}
