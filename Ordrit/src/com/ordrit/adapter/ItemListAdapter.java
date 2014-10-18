package com.ordrit.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ordrit.R;
import com.ordrit.newmodel.SubCategoryItem;
import com.ordrit.util.OrdritConstants;

public class ItemListAdapter extends ArrayAdapter<SubCategoryItem>{

	private List<SubCategoryItem> itemList;
	private List<SubCategoryItem> tempItemList;
	private Context context;
	private int textViewResourceId;
	
	public ItemListAdapter(Activity context, int textViewResourceId,
			List<SubCategoryItem> applicationList) {
		super(context, textViewResourceId, applicationList);
		this.context = context;
		this.textViewResourceId=textViewResourceId;
		this.itemList=applicationList;
		this.tempItemList=new ArrayList<SubCategoryItem>();
        this.tempItemList.addAll(itemList);
		this.context =  context;
	}

	private class ViewHolder {
		TextView productItemName;
		TextView productItemPrice;
		ImageView productItemImage;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		final SubCategoryItem item = itemList.get(position);
		//Log.v("ConvertView", String.valueOf(position));

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(textViewResourceId, null);

			holder = new ViewHolder();
			holder.productItemImage = (ImageView) convertView
					.findViewById(R.id.productItemImage);
			holder.productItemName = (TextView) convertView
					.findViewById(R.id.productItemName);
			holder.productItemPrice = (TextView) convertView
					.findViewById(R.id.productItemPrice);
			
			convertView.setTag(holder);
	
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		String productName = item.getName();
		String productPrice = item.getPrice();
		
	if (holder!=null) {
		
	
		if (productName != null && holder.productItemName!=null) {
			holder.productItemName.setText(""+productName);
		}
		if (productPrice != null& holder.productItemPrice!=null) {
			holder.productItemPrice.setText(""+productPrice);
			
		}
		ImageLoader.getInstance().displayImage(OrdritConstants.SERVER_BASE_URL+item.getImage().getImage(), holder.productItemImage);
		//displayImage(item.getImageURL(), holder.productItemImage, null);
	}
		return convertView;

	}
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		itemList.clear();
		if (charText.length() == 0) {
			itemList.addAll(tempItemList);
		} else {
			for (SubCategoryItem item : tempItemList) {
				if (item.getName().toLowerCase().contains(charText.toLowerCase())) {
					itemList.add(item);
				}
			}
		}
		notifyDataSetChanged();
	}
}
