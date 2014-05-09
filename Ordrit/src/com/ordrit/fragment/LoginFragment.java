package com.ordrit.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.activity.HomeActivity;
import com.ordrit.activity.MainActivity;
import com.ordrit.util.CalibriTextView;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.ServerConnection;

public class LoginFragment extends Fragment {

	private View loginFragment;
	private MainActivity mainActivity = null;
	private EditText editTextUserName, editTextPassword=null;
	private CalibriTextView editTextUserNameError, passwordError, SignUpText=null;
	private ProgressBar progressBarLogin;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity) getActivity();
		mainActivity.getActionBar().hide();
		loginFragment = inflater.inflate(R.layout.fragment_login, container,
				false);
		TextView headingText = (TextView) loginFragment
				.findViewById(R.id.login_heading);
		progressBarLogin= (ProgressBar) loginFragment
				.findViewById(R.id.progressBarLogin);
		editTextUserName=(EditText) loginFragment.findViewById(R.id.editTextUserName);
		editTextUserNameError= (CalibriTextView) loginFragment.findViewById(R.id.textUserNameError);
		passwordError= (CalibriTextView) loginFragment.findViewById(R.id.passwordError);
		editTextPassword=(EditText) loginFragment.findViewById(R.id.editTextPassword);
		SignUpText= (CalibriTextView) loginFragment.findViewById(R.id.signUpLink);
		SignUpText.setText(Html.fromHtml(getString(R.string.sign_up_link)));
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));

		Button loginButton = (Button) loginFragment
				.findViewById(R.id.buttonLogin);
		
		SignUpText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
				Animation anim = AnimationUtils.loadAnimation(
						mainActivity, R.anim.bottom_to_top);
				vi.startAnimation(anim);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				Fragment userSignUpFragment = new UserSignUp();
				
				fragmentTransaction.replace(R.id.application_container,
						userSignUpFragment);
				fragmentTransaction.addToBackStack("loginScreen");
				fragmentTransaction.commit();
				
			}
		});
		
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean errorFound= false;
				if(editTextUserName.getText().toString().length()==0){
					editTextUserNameError.setText(mainActivity.getResources().getString(R.string.username_field_empty));
					editTextUserNameError.setVisibility(View.VISIBLE);
					errorFound= true;
				}
				
				if(editTextPassword.getText().toString().length()==0){
					passwordError.setText(mainActivity.getResources().getString(R.string.password_field_empty));
					passwordError.setVisibility(View.VISIBLE);
					errorFound= true;
				}
				
				if(!errorFound){
					progressBarLogin.setVisibility(View.VISIBLE);
					if (new CommonUtils(getActivity()).isConnectingToInternet()) {
						getUserDataDataJSONString(editTextUserName.getText()
								.toString(), editTextPassword.getText()
								.toString());
						UserAuthenticationTask userAuthenticationTask = new UserAuthenticationTask(
								getUserDataDataJSONString(editTextUserName
										.getText().toString(), editTextPassword
										.getText().toString()),
								OrdritConstants.SERVER_BASE_URL+OrdritConstants.API_TOKEN);
						userAuthenticationTask.execute((Void) null);
					}else{
						Toast.makeText(
								getActivity(),
								getResources().getString(
										R.string.internet_connection_failed), Toast.LENGTH_LONG)
								.show();
					}
				}
				startActivity(new Intent(mainActivity, HomeActivity.class));
				}
		});

		return loginFragment;
	}

	// Create Registration data
	
	private String getUserDataDataJSONString(String username, String password) {
		String userCredentialsString = new String();
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_NAME, "username");
			userObject.put(OrdritJsonKeys.USER_PASSWORD, "password");
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		userCredentialsString = userObject.toString();

		return userCredentialsString;
	}

	
	
	// Create User Token
	
	private class UserAuthenticationTask extends AsyncTask<Void, Void, String> {

		String authenticationData = null;
		String requestUrl = null;
		String message = new String();

		public UserAuthenticationTask(String data, String type) {
			authenticationData = data;
			requestUrl = type;
		}

		@Override
		protected String doInBackground(Void... params) {
			ServerConnection connection = new ServerConnection();
			JSONObject response = connection.postHttpUrlConnection(
					authenticationData, requestUrl, null);		

			if (response != null) {
				//TODO
			}else{
				// ERROR OCCOURRED
				/*
				new Thread() {
		            public void run() {


		                    appLaunchActivity.runOnUiThread(new Runnable(){

		                         @Override
		                         public void run(){
		                        	 Toast.makeText(appLaunchActivity, "Something went wrong, Please try again later.",
		             						Toast.LENGTH_LONG).show();
		                        	 buttonRegister.setVisibility(View.VISIBLE);
		             				progressBarRegister.setVisibility(View.GONE);
		                         }
		                    });
		                    
		                   }
		              
		          }.start();
				
				
			*/}
			return message;
		}

		@Override
		protected void onPostExecute(final String status) {
			
		}
	}
	
}
