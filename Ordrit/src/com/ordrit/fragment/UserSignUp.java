package com.ordrit.fragment;

import javax.xml.datatype.Duration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
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
import com.ordrit.activity.MainActivity;
import com.ordrit.util.ValidationUtils;

public class UserSignUp extends Fragment {

	private View signUpFragment;
	private MainActivity mainActivity = null;
	private Button buttonSignUp;
	private EditText userNameEditText, passwordEditText,
			confirmPasswordEditText, emailIdEditText;
	private TextView editTextUserNameError,
			passwordError, editTextUserEmailIdError;
	private ProgressBar progressBarSignUp;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity) getActivity();
		mainActivity.getActionBar().hide();
		signUpFragment = inflater.inflate(R.layout.fragment_signup, container,
				false);
		progressBarSignUp= (ProgressBar) signUpFragment
				.findViewById(R.id.progressBarSignUp);
		TextView headingText = (TextView) signUpFragment
				.findViewById(R.id.signupHeading);
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));
		// EditText
		userNameEditText=  (EditText) signUpFragment.findViewById(R.id.editTextUserName);
		passwordEditText=  (EditText) signUpFragment.findViewById(R.id.editTextPassword);
		confirmPasswordEditText=  (EditText) signUpFragment.findViewById(R.id.editTextConfirmPassword);
		emailIdEditText=  (EditText) signUpFragment.findViewById(R.id.editTextUserEmailId);
		
		editTextUserNameError=  (TextView) signUpFragment.findViewById(R.id.textUserNameError);
		passwordError=  (TextView) signUpFragment.findViewById(R.id.passwordError);
		editTextUserEmailIdError=  (TextView) signUpFragment.findViewById(R.id.textUserEmailIdError);
		
		
		//Sing Up Button
		buttonSignUp= (Button) signUpFragment.findViewById(R.id.buttonSignUp);
		buttonSignUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearAllErrors();
				if(!fieldValidation()){
					
					progressBarSignUp.setVisibility(View.VISIBLE);
					View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
					Animation anim = AnimationUtils.loadAnimation(
							mainActivity, R.anim.bottom_to_top);
					vi.startAnimation(anim);
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					Fragment userloginFragment = new LoginFragment();
					
					fragmentTransaction.replace(R.id.application_container,
							userloginFragment);
					fragmentTransaction.addToBackStack("signUpScreen");
					fragmentTransaction.commit();
					Toast.makeText(mainActivity, R.string.sucess_regis, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		return signUpFragment;
	}
	
	private boolean fieldValidation(){
		boolean containsError = false;
		if(userNameEditText.getText().length()== 0){
			editTextUserNameError.setVisibility(View.VISIBLE);
			editTextUserNameError.setText(mainActivity.getResources().getString(R.string.empty_username_error));
			containsError = true;
		}
		
		if(passwordEditText.getText().length()== 0){
			passwordError.setVisibility(View.VISIBLE);
			passwordError.setText(mainActivity.getResources().getString(R.string.empty_password_error));
			containsError = true;
		}else{
			if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())){
				passwordError.setVisibility(View.VISIBLE);
				passwordError.setText(mainActivity.getResources().getString(R.string.unmatch_password_error));
				containsError = true;
			}
		}
		
		if(emailIdEditText.getText().length()== 0){
			editTextUserEmailIdError.setVisibility(View.VISIBLE);
			editTextUserEmailIdError.setText(mainActivity.getResources().getString(R.string.empty_email_error));
			containsError = true;
		}else if(!ValidationUtils.isEmailValid(emailIdEditText.getText().toString())){
			editTextUserEmailIdError.setVisibility(View.VISIBLE);
			editTextUserEmailIdError.setText(mainActivity.getResources().getString(R.string.invalid_email));
			containsError = true;
		}
		return containsError;
	}
	
	private void clearAllErrors(){
		editTextUserNameError.setVisibility(View.GONE);
		passwordError.setVisibility(View.GONE);
		editTextUserEmailIdError.setVisibility(View.GONE);
		
	}

}
