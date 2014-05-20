package com.ordrit.fragment;

import java.util.List;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ordrit.R;
import com.ordrit.adapter.IconizedWindowAdapter;
import com.ordrit.model.Store;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class MapDetailFragment extends BaseFragment {
	private final String tag = "MapDetailFragment";
	private static View mapDetailFragment;
	private GoogleMap googleMap;
	private Button menu, menuShareWithFriends, menuAddAddress;
	List<Store> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		

		  if (mapDetailFragment != null) {
		        ViewGroup parent = (ViewGroup) mapDetailFragment.getParent();
		        if (parent != null)
		            parent.removeView(mapDetailFragment);
		   }
		
	
				try {
					mapDetailFragment = inflater.inflate(R.layout.detail_map_fragment,
							container, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int status = GooglePlayServicesUtil
						.isGooglePlayServicesAvailable(getActivity());

				// Check Google Play Service Available
				try {
					if (status != ConnectionResult.SUCCESS) {
						GooglePlayServicesUtil.getErrorDialog(status, getActivity(), 1)
								.show();
					}
				} catch (Exception e) {
					Log.e("Error: GooglePlayServiceUtil: ", "" + e);
				}

				try {
					// Loading map
					initilizeMap();
                   // setupMapData();
					new WebServiceProcessingTask() {
						
						@Override
						public void preExecuteTask() {
						TAG=tag;
							
						}
						
						@Override
						public void postExecuteTask() {
							if (list!=null) {
								setupMapData(list);
							}
							
						}
						
						@Override
						public void backgroundTask() {
						
							jSONObject = connection.getHttpUrlConnection(
									OrdritConstants.SERVER_BASE_URL
											+ "stores?distance=100000&point=POINT%2877.128036+28.694839%29",
									SharedPreferencesUtil.getStringPreferences(
											dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
						
							try {
								 list=OrditJsonParser.getAllStoresFromJSON(jSONObject);
							} catch (JSONException e) {
								
								e.printStackTrace();
							}
							
						}
					}.execute();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
		setupUiComponent();

		return mapDetailFragment;
	}
   
     
	
	private void initilizeMap() {
		if (googleMap == null) {
			
		MapFragment mapFragment=(MapFragment) getActivity().getFragmentManager().findFragmentById(
				R.id.detailMap);
		
		googleMap=mapFragment.getMap();
		googleMap.setInfoWindowAdapter(new IconizedWindowAdapter(
                getActivity().getLayoutInflater()));
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	private void setupMapData(List<Store> list) {
		// Changing map type
		// googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		 googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		

		// Showing / hiding your current location
		googleMap.setMyLocationEnabled(true);

		// Enable / Disable zooming controls
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		// Enable / Disable my location button
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Enable / Disable Compass icon
		googleMap.getUiSettings().setCompassEnabled(true);

		// Enable / Disable Rotate gesture
		googleMap.getUiSettings().setRotateGesturesEnabled(true);

		// Enable / Disable zooming functionality
		googleMap.getUiSettings().setZoomGesturesEnabled(true);

		

		// lets place some 10 random markers
		for (int i = 0; i < list.size(); i++) {
			Store store=list.get(i);
			String locationLatLong= store.getLocationLatLong();
			
			double[] randomLocation = createRandLocation(locationLatLong);

			// Adding a marker
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(randomLocation[1], randomLocation[0]))
					.title(store.getStoreName()+":" + randomLocation[1]+","+ randomLocation[0]);
			
			Log.e("Random", "> " + randomLocation[0] + ", "
					+ randomLocation[1]);
			marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
			
			googleMap.addMarker(marker);
			
			

			// Move the camera to last position with a zoom level
			
				
			
		}
		CameraPosition cameraPosition = new CameraPosition.Builder()
		.target(new LatLng(28.49851388386366,
				77.08356393548581)).zoom(10).build();

googleMap.animateCamera(CameraUpdateFactory
		.newCameraPosition(cameraPosition));
	}
	
	private double[] createRandLocation(String locationLatLong) {
		
		locationLatLong = locationLatLong.substring(locationLatLong.indexOf("(") + 1);
		locationLatLong = locationLatLong.substring(0, locationLatLong.indexOf(")"));
		String[] splited = locationLatLong.split("\\s+");
		return new double []{Double.parseDouble(splited[0]),Double.parseDouble(splited[1])};
		
	}
	@Override
	public void onResume() {
		initilizeMap();
		super.onResume();
	}

	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	/*    try {
	        android.app.Fragment fragment =  dashboardActivity
	                                          .getFragmentManager().findFragmentById(
	                                              R.id.detailMap);
	        if (fragment != null) getFragmentManager().beginTransaction().remove(fragment).commit();

	    } catch (Exception e) {
	      
	    }*/
	}



	@Override
	void setupUiComponent() {
		menu = (Button) mapDetailFragment.findViewById(R.id.menu);
		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
		menuShareWithFriends = (Button) mapDetailFragment
				.findViewById(R.id.menuShareWithFriends);
		menuShareWithFriends.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				

			}
		});
		menuAddAddress = (Button) mapDetailFragment
				.findViewById(R.id.menuAddAddress);
		menuAddAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				

			}
		});
	}
	

}
