package com.ordrit.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.model.DrawerItem;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {
	 
    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;
    LayoutInflater inflater;

    public CustomDrawerAdapter(Context context, int layoutResourceID,
                List<DrawerItem> listItems) {
          super(context, layoutResourceID, listItems);
          this.context = context;
          this.drawerItemList = listItems;
          this.layoutResID = layoutResourceID;
          inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          // TODO Auto-generated method stub

          DrawerItemHolder drawerHolder;
          View view = convertView;

          if (view == null) {
              
                drawerHolder = new DrawerItemHolder();

                view = inflater.inflate(layoutResID, parent, false);
                drawerHolder.ItemName = (TextView) view
                            .findViewById(R.id.drawerListItemTitle);
                drawerHolder.icon = (ImageView) view.findViewById(R.id.drawerListItemImageIcon);

                view.setTag(drawerHolder);

          } else {
                drawerHolder = (DrawerItemHolder) view.getTag();

          }

          DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

          drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                      dItem.getIcon()));
          drawerHolder.ItemName.setText(dItem.getTitle());

          return view;
    }

    private static class DrawerItemHolder {
          TextView ItemName;
          ImageView icon;
    }
}
