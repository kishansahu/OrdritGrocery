package com.ordrit.fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.PrivateCredentialPermission;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.adapter.ExpandableListAdapter;
import com.ordrit.adapter.MenuBagAdapter;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.MenuBagItem;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class CatogeryFragment extends BaseFragment {
	
	private static final String tag="CatogeryFragment";
	private View catogeryFragment;
	private Button catogeryBack;
    private ExpandableListView catoreryListView;
    private ExpandableListAdapter expandableListAdapter;
    private Map<String, ItemCategory> itemCategoryMap = null;
    private String storeId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		catogeryFragment=inflater.inflate(R.layout.fragment_catogery, container,false);
		setupUiComponent();
		return catogeryFragment;
	}
	@Override
	void setupUiComponent() {
		
		catoreryListView=(ExpandableListView)catogeryFragment.findViewById(R.id.catoreryListView);
		catoreryListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				ItemSubCategory itemSubCategory=(ItemSubCategory) expandableListAdapter.getChild(groupPosition,childPosition);
				ItemListFragment itemListFragment =new ItemListFragment();
				Bundle bundle=new Bundle();
				bundle.putSerializable("data", itemSubCategory);
				//TODO
				// remove id
				bundle.putString(OrdritConstants.STORE_ID, "4");
				itemListFragment.setArguments(bundle);
				dashboardActivity.commitFragment(itemListFragment, FragmentConstant.ITEM_LIST_FRAGMENT);
				return false;
			}
		});
		catogeryBack=(Button)catogeryFragment.findViewById(R.id.catogeryBack); 
		catogeryBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.ITEM_LIST_FRAGMENT);
			}
		});
		
		
		new WebServiceProcessingTask() {
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
				
			}
			
			@Override
			public void postExecuteTask() {
				 expandableListAdapter =new ExpandableListAdapter(getItemCatogery(itemCategoryMap),dashboardActivity );
				catoreryListView.setAdapter(expandableListAdapter);
			}
			
			@Override
			public void backgroundTask() {
			
				jSONString = connection.getHttpUrlConnectionForArray(
						OrdritConstants.SERVER_BASE_URL
								+ "item_sub_categories?store=4",
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
				// Log.e(TAG,jSONString);
				try {
					 itemCategoryMap=OrditJsonParser.getItemCategoryMap(jSONString);
					  Log.e(TAG, ""+itemCategoryMap.size());
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				 Iterator it = itemCategoryMap.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next();
				       
				      //  Log.e(TAG, pairs.getKey()+ " "+(ItemCategory) pairs.getValue());
		//		        it.remove(); // avoids a ConcurrentModificationException
				    }
				
				
				
				
			}
		}.execute();
	}
	private List<ItemCategory> getItemCatogery(Map<String, ItemCategory> itemCategoryMap) {
		List<ItemCategory> listItemvCategories = new ArrayList<ItemCategory>();
		
		 Iterator it = itemCategoryMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        if (!listItemvCategories.contains((ItemCategory) pairs.getValue())) {
		        	 listItemvCategories.add((ItemCategory) pairs.getValue());
				}
		       
		       // it.remove(); // avoids a ConcurrentModificationException
		    }
		
		return listItemvCategories;
		
	}

}
