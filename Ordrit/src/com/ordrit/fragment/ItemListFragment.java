package com.ordrit.fragment;

import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

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
	private EditText search;
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
		itemListListGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemDetailFragment itemListFragment =new ItemDetailFragment();
				Bundle bundle=new Bundle();
				bundle.putSerializable(OrdritConstants.ITEM, itemListAdapter.getItem(position));
				//TODO
				// remove id
				bundle.putString(OrdritConstants.STORE_ID, storeId);
				itemListFragment.setArguments(bundle);
				dashboardActivity.commitFragment(itemListFragment, FragmentConstant.ITEM_DETAIL_FRAGMENT);
			}
		});
		itemListBack=(Button)itemListFragment.findViewById(R.id.itemListBack); 
		itemListBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.ITEM_LIST_FRAGMENT);
			}
		});
        search=(EditText)itemListFragment.findViewById(R.id.edittextSearch);
        search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String text = search.getText().toString()
						.toLowerCase(Locale.getDefault());
			if (itemListAdapter!=null) {
				itemListAdapter.filter(text);
				
			}
				
			}
		});
 if (itemList==null) {
	new WebServiceProcessingTask() {
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
			progressDialog=new ProgressDialog(dashboardActivity);
				
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
}else {
	itemListListGridView.setAdapter(itemListAdapter);
}
 dashboardActivity.checkCartItems(itemListFragment);
		
	}

}
