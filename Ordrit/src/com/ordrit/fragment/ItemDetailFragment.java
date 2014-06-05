package com.ordrit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ordrit.R;
import com.ordrit.model.Item;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;

public class ItemDetailFragment extends BaseFragment {
	
	private static final String tag="ItemListFragment";
	private View itemDetailFragment;
	private Button itemDetailBack;
	private String storeId;
	TextView textItemName,itemPrice;
	ImageView imageViewItemImage;
    private Item item;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		itemDetailFragment=inflater.inflate(R.layout.fragment_item_details, container,false);
		Bundle bundle=getArguments();
		item = (Item)bundle.getSerializable(OrdritConstants.ITEM);
		storeId=bundle.getString(OrdritConstants.STORE_ID);
		setupUiComponent();
		return itemDetailFragment;
	}
	@Override
	void setupUiComponent() {
	

		itemDetailBack=(Button)itemDetailFragment.findViewById(R.id.itemDetailBack); 
		itemDetailBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.ITEM_DETAIL_FRAGMENT);
			}
		});
       
		textItemName=(TextView)itemDetailFragment.findViewById(R.id.textItemName); 
		textItemName.setText(item.getName());
		itemPrice=(TextView)itemDetailFragment.findViewById(R.id.itemPrice); 
		itemPrice.setText(item.getPricePerUnit());
		imageViewItemImage=(ImageView)itemDetailFragment.findViewById(R.id.imageViewItemImage);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(item.getImageURL(), imageViewItemImage);
		NumberPicker numberPicker = (NumberPicker)itemDetailFragment.findViewById(R.id.numberPicker);
	        numberPicker.setMaxValue(100);    
	        numberPicker.setMinValue(0);        
	        numberPicker.setWrapSelectorWheel(true);
	        numberPicker.setOnValueChangedListener( new NumberPicker.
	            OnValueChangeListener() {
	            @Override
	            public void onValueChange(NumberPicker picker, int
	                oldVal, int newVal) {
	               
	            }
	        });
	}

}
