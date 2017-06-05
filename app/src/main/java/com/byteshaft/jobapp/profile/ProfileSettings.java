
package com.byteshaft.jobapp.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.jobapp.MainActivity;
import com.byteshaft.jobapp.R;

/**
 * Created by husnain on 6/1/17.
 */

public class ProfileSettings extends AppCompatActivity implements View.OnClickListener{

    private TextView mLogoutTextView;
    private TextView mFeedbackTextView;
    private TextView mLanguageTextView;
    private TextView mTermsTextView;
    private TextView mAppVersionTextView;
    private TextView mAppPrivacyTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.byteshaft.jobapp.R.layout.activity_settings);
        getSupportActionBar().hide();
        mLogoutTextView = (TextView) findViewById(R.id.logout_text_view);
        mFeedbackTextView = (TextView) findViewById(R.id.feedback_text_view);
        mLanguageTextView = (TextView) findViewById(R.id.language_text_view);
        mTermsTextView = (TextView) findViewById(R.id.terms_text_view);
        mAppVersionTextView = (TextView) findViewById(R.id.app_version_text_view);
        mAppPrivacyTextView = (TextView) findViewById(R.id.policy_text_view);

        mLogoutTextView.setOnClickListener(this);
        mFeedbackTextView.setOnClickListener(this);
        mLanguageTextView.setOnClickListener(this);
        mTermsTextView.setOnClickListener(this);
        mAppVersionTextView.setOnClickListener(this);
        mAppPrivacyTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_text_view:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Confirmation");
                alertDialogBuilder.setMessage("Do you really want to logout?")
                        .setCancelable(false).setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.feedback_text_view:
                Toast.makeText(getApplicationContext(), "soon it will be implement", Toast.LENGTH_SHORT).show();
                break;
            case R.id.language_text_view:
                Toast.makeText(getApplicationContext(), "soon it will be implement", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms_text_view:
                Toast.makeText(getApplicationContext(), "soon it will be implement", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_version_text_view:
                Toast.makeText(getApplicationContext(), "soon it will be implement", Toast.LENGTH_SHORT).show();
                break;
            case R.id.policy_text_view:
                Toast.makeText(getApplicationContext(), "soon will it be implement", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}


