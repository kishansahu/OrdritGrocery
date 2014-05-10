package com.ordrit.adapter;

import java.util.List;

import com.ordrit.model.MenuBagItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MenuBagAdapter extends ArrayAdapter<MenuBagItem>{
	
 private List<MenuBagItem>	menuBagItemList;
 private int resourceId;
 private LayoutInflater inflater;

	public MenuBagAdapter(Context context, int resourceId,
			List<MenuBagItem> menuBagItemList) {
		super(context, resourceId, menuBagItemList);
		this.menuBagItemList=menuBagItemList;
		this.resourceId=resourceId;
	    inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	View view=inflater.inflate(resourceId, parent, false);
	
		return view;
	}
}
