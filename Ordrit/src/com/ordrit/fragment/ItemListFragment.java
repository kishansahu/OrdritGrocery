package com.ordrit.fragment;

import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.DashboardActivity;
import com.ordrit.adapter.ItemListAdapter;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.model.MenuData;
import com.ordrit.newmodel.SubCategoryData;
import com.ordrit.newmodel.SubCategoryItem;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class ItemListFragment extends BaseFragment {
	
	private static final String tag="ItemListFragment";
	private View itemListFragment;
	private ImageButton switchView;
	private EditText search;
    private GridView itemListListGridView;
    private ListView itemListListListView;
    private ItemListAdapter itemListAdapter,itemListAdapterList;
    private List<SubCategoryItem> itemList;
    private MenuData menuData;
    private OrdrItdataBaseHelper ordrItdataBaseHelper;
    SubCategoryData subCategoryData;
    
    //public static Store store;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		itemListFragment=inflater.inflate(R.layout.fragment_item_list, container,false);
		
		return itemListFragment;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupUiComponent();
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
				itemListFragment.setArguments(bundle);
				dashboardActivity.commitFragment(itemListFragment, FragmentConstant.ITEM_DETAIL_FRAGMENT);
			}
		});
		itemListListListView=(ListView)itemListFragment.findViewById(R.id.itemListListListView);
		itemListListListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemDetailFragment itemListFragment =new ItemDetailFragment();
				Bundle bundle=new Bundle();
				bundle.putSerializable(OrdritConstants.ITEM, itemListAdapterList.getItem(position));
				itemListFragment.setArguments(bundle);
				dashboardActivity.commitFragment(itemListFragment, FragmentConstant.ITEM_DETAIL_FRAGMENT);
			}
		});
		ImageButton itemListBack = (ImageButton)itemListFragment.findViewById(R.id.itemListBack); 
		itemListBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
		switchView=(ImageButton)itemListFragment.findViewById(R.id.switchView); 
		switchView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout.LayoutParams fieldparams = new LinearLayout.LayoutParams(
						CommonUtils.convertDensityPixelToPixel(dashboardActivity,
								30), CommonUtils.convertDensityPixelToPixel(
								dashboardActivity,30), 0);
				fieldparams.gravity = Gravity.CENTER;
				if (itemListListGridView.getVisibility()==View.VISIBLE) {
					itemListListGridView.setVisibility(View.GONE);
					itemListListListView.setVisibility(View.VISIBLE);
					switchView.setBackgroundResource(R.drawable.list_ico);
					
				}else {
					itemListListListView.setVisibility(View.GONE);
					itemListListGridView.setVisibility(View.VISIBLE);
					switchView.setBackgroundResource(R.drawable.grid_ico);
				}
				
				switchView.setLayoutParams(fieldparams);
			}
		});
		 ordrItdataBaseHelper = new OrdrItdataBaseHelper(dashboardActivity);
				 TextView delevaryTime = (TextView)itemListFragment.findViewById(R.id.delevaryTime);
				 String time="The next available delivery slot at "+DashboardActivity.store.getOpens_at()
							+ " to "
							+DashboardActivity.store.getCloses_at();
				 delevaryTime.setText(time);
		         search=(EditText)itemListFragment.findViewById(R.id.edittextSearch);
		         search.setHint("Search "+DashboardActivity.store.getName());
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
	new WebServiceProcessingTask(dashboardActivity) {
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
			
				
			}
			
			@Override
			public void postExecuteTask() {
				if (subCategoryData!=null) {
					itemList=subCategoryData.getResults();
					itemListAdapter = new ItemListAdapter(dashboardActivity, R.layout.product_item, itemList);
					itemListAdapterList = new ItemListAdapter(dashboardActivity, R.layout.product_item_list, itemList);
					itemListListGridView.setAdapter(itemListAdapter);
					itemListListListView.setAdapter(itemListAdapterList);
				}
			
				
			}
			
			@Override
			public void backgroundTask() {
			
				Bundle bundle=getArguments();
				String storeId=bundle.getString("storeId");
				String catogeryId=bundle.getString("catogeryId");
				String subCategoryId=bundle.getString("subCategoryId");
			
				jSONString = connection.getHttpUrlConnectionForArray(
						OrdritConstants.SERVER_BASE_URL
								+ "inventory_items?store="+storeId+"&sub_category="+subCategoryId,
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
					/*	OrdritConstants.SERVER_BASE_URL
						+"item_categories/"+ "item_sub_categories?store="+DashboardActivity.store.getId(),
				SharedPreferencesUtil.getStringPreferences(
						dashboardActivity, OrdritJsonKeys.TAG_TOKEN));*/
			//	 Log.e(TAG,jSONString);
				try {
					//itemList=OrditJsonParser.getItemsUnderSubCategory(DashboardActivity.store.getId(), menuData.getSubCategory(),jSONString);
					 subCategoryData= gson.fromJson(jSONString, SubCategoryData.class);
				
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				 
				
			}
		}.execute();
}else {
	itemListListGridView.setAdapter(itemListAdapter);
	itemListListListView.setAdapter(itemListAdapterList);
}
 dashboardActivity.checkCartItems(itemListFragment);
		
	}

}
