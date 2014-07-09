package com.ordrit.fragment;

import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.adapter.PreviousOrdersAdapter;
import com.ordrit.model.Order;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class PreviousOrdersFragment extends BaseFragment {
	private final String tag = "OrderStatusFragment";
	private View previousOrdersFragment;
	private List<Order> ordersList;
	private Button back;
	private ListView previosOrdersListView;
	private UILApplication uilApplication;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		previousOrdersFragment = inflater.inflate(R.layout.fragment_previous_orders,
				container, false);
		uilApplication = (UILApplication) dashboardActivity.getApplication();
		setupUiComponent();
		return previousOrdersFragment;
	}

	@Override
	public void onResume() {
		if (new CommonUtils(getActivity()).isConnectingToInternet()) {

			new WebServiceProcessingTask() {

				@Override
				public void preExecuteTask() {
					TAG = tag;
					progressDialog=new ProgressDialog(dashboardActivity);
				}

				@Override
				public void postExecuteTask() {
					PreviousOrdersAdapter previousOrdersAdapter = new PreviousOrdersAdapter(dashboardActivity,
							R.layout.item_previous_order, ordersList);
					previosOrdersListView.setAdapter(previousOrdersAdapter);

				}

				@Override
				public void backgroundTask() {

					// Start test code for fetching the orders placed
					jSONString = connection.getHttpUrlConnectionForArray(
							OrdritConstants.SERVER_BASE_URL
									+ OrdritConstants.ORDERS,
							SharedPreferencesUtil.getStringPreferences(
									getActivity(), OrdritJsonKeys.TAG_TOKEN));
					Log.e("order json", jSONString);
					ordersList=	OrditJsonParser.getPreviousOrders(jSONString);
					// End test code for fetching the orders placed

				}
			}.execute();

		} else {
			Toast.makeText(
					getActivity(),
					getResources().getString(
							R.string.internet_connection_failed),
					Toast.LENGTH_LONG).show();
		}
		super.onResume();
	}

	@Override
	void setupUiComponent() {
		back = (Button) previousOrdersFragment.findViewById(R.id.back);
		boolean showBack = false;
		try {
			showBack = getArguments().getBoolean(
					OrdritConstants.SHOW_BACK_BUTTON);
		} catch (Exception e) {
			String s = e.toString();
			// TODO: handle exception
		}
		if (showBack) {
			back.setBackgroundResource(R.drawable.ic_action_previous_item);
			LinearLayout.LayoutParams fieldparams = new LinearLayout.LayoutParams(
					CommonUtils.convertDensityPixelToPixel(dashboardActivity,
							40), CommonUtils.convertDensityPixelToPixel(
							dashboardActivity, 40), 0);
			fieldparams.gravity = Gravity.CENTER;
			fieldparams.setMargins(CommonUtils.convertDensityPixelToPixel(
					dashboardActivity, 10), CommonUtils
					.convertDensityPixelToPixel(dashboardActivity, 0),
					CommonUtils
							.convertDensityPixelToPixel(dashboardActivity, 0),
					CommonUtils
							.convertDensityPixelToPixel(dashboardActivity, 0));
			back.setLayoutParams(fieldparams);
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dashboardActivity
							.popFragment(FragmentConstant.ORDER_STATUS_FRAGMENT);
				}
			});
		} else {
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dashboardActivity.clickMenu();
				}
			});
		}
		previosOrdersListView= (ListView) previousOrdersFragment.findViewById(R.id.previosOrdersListView);
	}

}