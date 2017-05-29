package com.byteshaft.jobapp.accounts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;



public class Register extends Fragment implements View.OnClickListener, HttpRequest.OnReadyStateChangeListener, HttpRequest.OnErrorListener {

    private View mBaseView;
    private EditText mName;
    private EditText mPhoneNumber;

    private EditText mEmail;
    private EditText mPassword;
    private EditText mVerifyPassword;
    private Button mSignUpButton;
    private TextView mLoginTextView;

    private String mEmailAddressString;
    private String mPasswordString;
    private String mVerifyPasswordString;
    private String mNameString;
    private String mPhoneString;
    private String mAccountType = String.valueOf(1);

    private HttpRequest request;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_register, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.sing_up));
        setHasOptionsMenu(true);

        mName = (EditText) mBaseView.findViewById(R.id.user_name_edit_text);
        mPhoneNumber = (EditText) mBaseView.findViewById(R.id.phone_number_edit_text);
        mEmail = (EditText) mBaseView.findViewById(R.id.email_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mVerifyPassword = (EditText) mBaseView.findViewById(R.id.verify_password_edit_text);
        mSignUpButton = (Button) mBaseView.findViewById(R.id.sign_up_button);
        mLoginTextView = (TextView) mBaseView.findViewById(R.id.login_text_view);

        mSignUpButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                System.out.println("signUp button");
                if (validateEditText()) {
//                    registerUser(mNameString, mPhoneString, mPasswordString, mEmailAddressString, mAccountType);
                }
                break;
            case R.id.login_text_view:
                AccountManager.getInstance().loadFragment(new Login());
                break;
        }
    }


    private boolean validateEditText() {
        boolean valid = true;
        mNameString = mName.getText().toString();
        mPhoneString = mPhoneNumber.getText().toString();
        mEmailAddressString = mEmail.getText().toString();
        mPasswordString = mPassword.getText().toString();
        mVerifyPasswordString = mVerifyPassword.getText().toString();

        if (mNameString.trim().isEmpty()) {
            mName.setError("Please provide a Name");
        } else {
            mName.setError(null);
        }

        if (mPhoneString.trim().isEmpty()) {
            mPhoneNumber.setError("Please provide phone number");
        } else {
            mPhoneNumber.setError(null);
        }

        if (mEmailAddressString.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mEmailAddressString).matches()) {
            mEmail.setError("please provide a valid email");
            valid = false;
        } else {
            mEmail.setError(null);
        }
        if (mPasswordString.trim().isEmpty() || mPasswordString.length() < 4) {
            mPassword.setError("enter at least 4 characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        if (mVerifyPasswordString.trim().isEmpty() || mVerifyPasswordString.length() < 4 ||
                !mVerifyPasswordString.equals(mPasswordString)) {
            mVerifyPassword.setError("password does not match");
            valid = false;
        } else {
            mVerifyPassword.setError(null);
        }
        return valid;
    }

    private void registerUser(String userName, String phonneNumber, String password, String email, String accountType) {
        request = new HttpRequest(getActivity());
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("POST", String.format("%sregister", AppGlobals.BASE_URL));
        request.send(getRegisterData(userName, phonneNumber, password, email, accountType));
        Helpers.showProgressDialog(getActivity(), "Registering User ");
    }

    private String getRegisterData(String userName, String phoneNumber, String password, String email, String accountType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("full_name", userName);
            jsonObject.put("phone_number", phoneNumber);
            jsonObject.put("account_type", accountType);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {
        switch (readyState) {
            case HttpRequest.STATE_DONE:
                Helpers.dismissProgressDialog();
                Log.i("TAG", "Response " + request.getResponseText());
                switch (request.getStatus()) {
                    case HttpRequest.ERROR_NETWORK_UNREACHABLE:
                        AppGlobals.alertDialog(getActivity(), "Registration Failed!", "please check your internet connection");
                        break;
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        AppGlobals.alertDialog(getActivity(), "Registration Failed!", "Email already in use");
                        break;
                    case HttpURLConnection.HTTP_CREATED:
                        Toast.makeText(getActivity(), "Activation code has been sent to you! Please check your Email", Toast.LENGTH_SHORT).show();
                        System.out.println(request.getResponseText() + "working ");
                        try {
                            JSONObject jsonObject = new JSONObject(request.getResponseText());
//                            String accountType = jsonObject.getString(AppGlobals.KEY_ACCOUNT_TYPE);
//                            String userId = jsonObject.getString(AppGlobals.KEY_USER_ID);
//                            String email = jsonObject.getString(AppGlobals.KEY_EMAIL);
//                            String userName = jsonObject.getString(AppGlobals.KEY_USER_NAME);
//                            String phoneNumber = jsonObject.getString(AppGlobals.KEY_PHONE_NUMBER);
//
//                            //saving values
//                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_ACCOUNT_TYPE, accountType);
//                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_EMAIL, email);
//                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_USER_ID, userId);
//                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_PHONE_NUMBER, phoneNumber);
//                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_USER_NAME, userName);
//                            Log.i("closingTime", " " + AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_PHONE_NUMBER));
                            AccountManager.getInstance().loadFragment(new AccountActivationCode());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
        }
    }

    @Override
    public void onError(HttpRequest request, int readyState, short error, Exception exception) {
        Helpers.dismissProgressDialog();
        switch (readyState) {
            case HttpRequest.ERROR_CONNECTION_TIMED_OUT:
                Helpers.showSnackBar(getView(), "connection time out");
                break;
            case HttpRequest.ERROR_NETWORK_UNREACHABLE:
                Helpers.showSnackBar(getView(), exception.getLocalizedMessage());
                break;
        }

    }
}
