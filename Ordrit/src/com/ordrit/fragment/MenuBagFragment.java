package com.ordrit.fragment;

import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.adapter.MenuBagAdapter;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.model.Item;
import com.ordrit.model.NavDrawerItem;
import com.ordrit.model.SelectedItem;
import com.ordrit.model.Store;
import com.ordrit.util.FragmentConstant;

public class MenuBagFragment extends BaseFragment {
	
	private View menuFragment;
	private Button menuBagBack,menuBagCheckout;
    private ListView menuBagListView;
    private TextView textMerchantName,textItemTotal;
    private UILApplication uilApplication;
    private	List<SelectedItem> selectedItemList;;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment=inflater.inflate(R.layout.fragment_menu_bag, container,false);
		uilApplication =(UILApplication) dashboardActivity.getApplication();
		selectedItemList=uilApplication.getSelectedItemList();
		setupUiComponent();
		return menuFragment;
	}
	@Override
	void setupUiComponent() {
		
		MenuBagAdapter menuBagAdapter= new MenuBagAdapter(dashboardActivity, R.layout.item_bag, selectedItemList);
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
		textMerchantName=(TextView)menuFragment.findViewById(R.id.textMerchantName); 
		OrdrItdataBaseHelper  ordrItdataBaseHelper=new OrdrItdataBaseHelper(dashboardActivity);
		textMerchantName.setText(ordrItdataBaseHelper.getStoreName(uilApplication.getStoreId()));
		textItemTotal=(TextView)menuFragment.findViewById(R.id.textItemTotal); 
		textItemTotal.setText(getTotalPrice());
		
		
	}
private String getTotalPrice() {
	float total=0;
	Iterator<SelectedItem> iterator= selectedItemList.iterator();
	while (iterator.hasNext()) {
		SelectedItem selectedItem=iterator.next();
		final Item item =selectedItem.getItem();
		float totalprice=Float.parseFloat(item.getPricePerUnit());
		totalprice=totalprice*(Integer.parseInt(selectedItem.getQuantity()));
		total=total+totalprice;
	}
	return String.valueOf(total);
	
	
	
	
	
}
}
