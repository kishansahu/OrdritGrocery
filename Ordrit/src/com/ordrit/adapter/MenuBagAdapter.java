package com.ordrit.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ordrit.R;
import com.ordrit.model.Item;
import com.ordrit.model.SelectedItem;

public class MenuBagAdapter extends ArrayAdapter<SelectedItem>{
	
	private	List<SelectedItem> selectedItemList;
 private int resourceId;
 private LayoutInflater inflater;
 private ImageLoader imageLoader;

	public MenuBagAdapter(Context context, int resourceId,
			List<SelectedItem> selectedItemList) {
		super(context, resourceId, selectedItemList);
		this.selectedItemList=selectedItemList;
		this.resourceId=resourceId;
	    inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=ImageLoader.getInstance();
	}
	
	private class ViewHolder {
		TextView itemPrice;
		TextView textItemTotal;
		TextView textProductName;
		ImageView imageViewItemImage;
		
	}
@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	final ViewHolder holder ;
	if (convertView == null) {
		holder=new ViewHolder();
		convertView=inflater.inflate(resourceId, parent, false);
		holder.itemPrice=(TextView)convertView.findViewById(R.id.itemPrice);
		holder.textItemTotal=(TextView)convertView.findViewById(R.id.textItemTotal);
		holder.textProductName=(TextView)convertView.findViewById(R.id.textProductName);
		holder.imageViewItemImage=(ImageView)convertView.findViewById(R.id.imageViewItemImage);
		convertView.setTag(holder);
	}else {
		  holder = (ViewHolder) convertView.getTag();
	}
	
	final SelectedItem selectedItem =selectedItemList.get(position);
	final Item item =selectedItem.getItem();
	
	float totalprice=Float.parseFloat(item.getPricePerUnit());
	totalprice=totalprice*(Integer.parseInt(selectedItem.getQuantity()));
	holder.itemPrice.setText(String.valueOf(totalprice));
	holder.textItemTotal.setText(selectedItem.getQuantity());
	holder.textProductName.setText(item.getName());
	imageLoader.displayImage(item.getImageURL(), holder.imageViewItemImage);
	

		return convertView;
	}
}
