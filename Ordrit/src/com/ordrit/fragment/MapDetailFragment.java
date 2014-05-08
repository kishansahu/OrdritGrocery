package com.ordrit.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ordrit.R;

public class MapDetailFragment extends BaseFragment {
private static 	View detailMapFragment;
	private GoogleMap googleMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		

		  if (detailMapFragment != null) {
		        ViewGroup parent = (ViewGroup) detailMapFragment.getParent();
		        if (parent != null)
		            parent.removeView(detailMapFragment);
		   }
		
	
				try {
					detailMapFragment = inflater.inflate(R.layout.detail_map_fragment,
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

					double latitude = 28.6666700;
					double longitude = 77.2166700;

					// lets place some 10 random markers
					for (int i = 0; i < 10; i++) {
						// random latitude and logitude
						double[] randomLocation = createRandLocation(latitude,
								longitude);

						// Adding a marker
						MarkerOptions marker = new MarkerOptions().position(
								new LatLng(randomLocation[0], randomLocation[1]))
								.title("Hello Maps " + (i+10));
						
						Log.e("Random", "> " + randomLocation[0] + ", "
								+ randomLocation[1]);

						// changing marker color
						if (i == 0)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 1)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 2)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 3)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 4)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 5)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 6)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 7)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 8)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
						if (i == 9)
							marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_red));
							/*marker.icon(BitmapDescriptorFactory
									.defaultMarker(BitmapDescriptorFactory.HUE_RED));*/

						googleMap.addMarker(marker);
						

						// Move the camera to last position with a zoom level
						if (i == 9) {
							CameraPosition cameraPosition = new CameraPosition.Builder()
									.target(new LatLng(randomLocation[0],
											randomLocation[1])).zoom(15).build();

							googleMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cameraPosition));
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
		

		return detailMapFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	
	private void initilizeMap() {
		if (googleMap == null) {
			
		MapFragment mapFragment=(MapFragment) getActivity().getFragmentManager().findFragmentById(
				R.id.detailMap);
		
		googleMap=mapFragment.getMap();
	/*	googleMap.setInfoWindowAdapter(new IconizedWindowAdapter(
                getActivity().getLayoutInflater()));*/
			/*googleMap = ( (MapFragment) getActivity().getFragmentManager().findFragmentById(
					R.id.map1)).getMap();*/
			
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	private double[] createRandLocation(double latitude, double longitude) {

		return new double[] { latitude + ((Math.random() - 0.8) / 100),
				longitude + ((Math.random() - 0.8) / 100),
				500 + ((Math.random() - 0.5) * 20) };
	}
	@Override
	public void onResume() {
		initilizeMap();
		super.onResume();
	}

	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    try {
	        android.app.Fragment fragment =  homeActivity
	                                          .getFragmentManager().findFragmentById(
	                                              R.id.detailMap);
	        if (fragment != null) getFragmentManager().beginTransaction().remove(fragment).commit();

	    } catch (IllegalStateException e) {
	        //handle this situation because you are necessary will get 
	        //an exception here :-(
	    }
	}
	
}
