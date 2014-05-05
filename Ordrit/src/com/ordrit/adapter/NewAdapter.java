package com.ordrit.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ordrit.R;
import com.ordrit.model.DrawerItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unchecked")
public class NewAdapter extends BaseExpandableListAdapter {

	public List<DrawerItem> groupItem;
	List<String> tempChild;
	public ArrayList<Object> Childtem = new ArrayList<Object>();
	public LayoutInflater inflater;
	//public Activity activity;
	private final Context context;

	public NewAdapter(Context context,List<DrawerItem> groupItem, ArrayList<Object> childItem) {
		this.context = context;
		this.groupItem = groupItem;
		this.Childtem = childItem;
		 inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

/*	public void setInflater(LayoutInflater mInflater, Activity act) {
		this.minflater = mInflater;
		activity = act;
	}
*/
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		tempChild = (ArrayList<String>) Childtem.get(groupPosition);
		TextView text = null;
		if (convertView == null) {
			//convertView = new TextView(context);
			convertView = inflater.inflate(R.layout.list_item_drawer_chield, null);
		}
		text = (TextView) convertView.findViewById(R.id.drawerListItemChieldTitle);
		text.setText(">"+tempChild.get(childPosition));
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, tempChild.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});
		convertView.setTag(tempChild.get(childPosition));
		return convertView;
	}

	

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) Childtem.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return groupItem.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
	
		if (convertView == null) {
			//convertView = new TextView(context);
			LayoutInflater inflater1 = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater1.inflate(R.layout.list_item_drawer_group, null);
		}
		CheckedTextView checkedTextTitle =(CheckedTextView) convertView.findViewById(R.id.drawerListItemGroupTitle);
		checkedTextTitle.setText(groupItem.get(groupPosition).getTitle());
		//checkedTextTitle.setChecked(isExpanded);
		convertView.setTag(groupItem.get(groupPosition));
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
