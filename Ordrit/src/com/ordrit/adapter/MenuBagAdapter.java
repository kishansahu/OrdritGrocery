package com.ordrit.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ordrit.R;
import com.ordrit.activity.DashboardActivity;
import com.ordrit.activity.UILApplication;
import com.ordrit.model.Item;
import com.ordrit.model.SelectedItem;

public class MenuBagAdapter extends ArrayAdapter<SelectedItem>{
	
	private	List<SelectedItem> selectedItemList;
 private int resourceId;
 private LayoutInflater inflater;
 private ImageLoader imageLoader;
 private UILApplication uilApplication;
 private Context context;
 private SetTotalCost setTotalCost;

	public MenuBagAdapter(Context context, int resourceId,
			List<SelectedItem> selectedItemList,UILApplication uilApplication,SetTotalCost setTotalCost) {
		super(context, resourceId, selectedItemList);
		this.context=context;
		this.selectedItemList=selectedItemList;
		this.resourceId=resourceId;
		this.uilApplication=uilApplication;
		this.setTotalCost=setTotalCost;
	    inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=ImageLoader.getInstance();
	}
	
	

	private class ViewHolder {
		TextView itemPrice;
		TextView textItemTotal;
		TextView textProductName;
		Button buttonDelete;
		Button buttonEdit;
		ImageView imageViewItemImage;
		
	}
@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
	final ViewHolder holder ;
	if (convertView == null) {
		holder=new ViewHolder();
		convertView=inflater.inflate(resourceId, parent, false);
		holder.itemPrice=(TextView)convertView.findViewById(R.id.itemPrice);
		holder.textItemTotal=(TextView)convertView.findViewById(R.id.textItemTotal);
		holder.textProductName=(TextView)convertView.findViewById(R.id.textProductName);
		holder.buttonDelete=(Button)convertView.findViewById(R.id.buttonDelete);
		holder.buttonEdit=(Button)convertView.findViewById(R.id.buttonEdit);
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
	holder.buttonDelete.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
		 
					// set title
					alertDialogBuilder.setTitle("Delete");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Do you want to delete ?")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
								selectedItemList.remove(position);
								notifyDataSetChanged();
								uilApplication.setSelectedItemList(selectedItemList);
								setTotalCost.setTotal();
								dialog.dismiss();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
			
		}
	});
	holder.buttonEdit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
		return convertView;
	}
public interface SetTotalCost{
	public void setTotal() ;
}
}
