package com.ordrit.fragment;

import java.util.List;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.ordrit.R;
import com.ordrit.adapter.ItemListAdapter;
import com.ordrit.model.Item;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class ItemListFragment extends BaseFragment {
	
	private static final String tag="ItemListFragment";
	private View itemListFragment;
	private Button itemListBack;
    private GridView itemListListGridView;
    private ItemListAdapter itemListAdapter;
    private List<Item> itemList;
    private String storeId;
    private ItemSubCategory itemSubCategory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		itemListFragment=inflater.inflate(R.layout.fragment_item_list, container,false);
		Bundle bundle=getArguments();
		itemSubCategory = (ItemSubCategory)bundle.getSerializable("data");
		storeId=bundle.getString(OrdritConstants.STORE_ID);
		setupUiComponent();
		return itemListFragment;
	}
	@Override
	void setupUiComponent() {
	
		itemListListGridView=(GridView)itemListFragment.findViewById(R.id.itemListListGridView);
		itemListBack=(Button)itemListFragment.findViewById(R.id.itemListBack); 
		itemListBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.MENU_BAG_FRAGMENT);
			}
		});
		

		new WebServiceProcessingTask() {
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
				
			}
			
			@Override
			public void postExecuteTask() {
				itemListAdapter = new ItemListAdapter(dashboardActivity, R.layout.product_item, itemList);
				itemListListGridView.setAdapter(itemListAdapter);
				
			}
			
			@Override
			public void backgroundTask() {
			
				jSONString = connection.getHttpUrlConnectionForArray(
						OrdritConstants.SERVER_BASE_URL
								+ "inventory_items",
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
			//	 Log.e(TAG,jSONString);
				try {
					itemList=OrditJsonParser.getItemsUnderSubCategory(storeId, itemSubCategory.getId(),jSONString);
					 
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				 
				
			}
		}.execute();
		
	}

}
