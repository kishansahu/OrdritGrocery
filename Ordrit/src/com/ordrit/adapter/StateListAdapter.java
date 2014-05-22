package com.ordrit.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ordrit.R;
import com.ordrit.model.States;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StateListAdapter extends ArrayAdapter<States> {

	private Context context;
	private List<States> list;

	public StateListAdapter(Context context, int textViewResourceId,
			List<States> items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.list = items;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.states_list_item, null);
		}

		States states = list.get(position);
		if (states != null) {
			// My layout has only one TextView
			TextView itemView = (TextView) view
					.findViewById(R.id.stateListItem);
			if (itemView != null) {
				// do whatever you want with your string and long
				itemView.setText(states.getName());
			}
		}

		return view;
	}

}
