package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;


public class PersonalSkills extends AppCompatActivity implements View.OnClickListener, HttpRequest.OnReadyStateChangeListener, HttpRequest.OnErrorListener {


    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;

    private EditText skillsEdittex;
    private HttpRequest request;
    private String skills;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_skills);

        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_skills_save);
        backButton = (ImageButton) findViewById(R.id.back_button);
        skillsEdittex = (EditText) findViewById(R.id.skills_edit_text);
        skillsEdittex.setText(AppGlobals.getStringFromSharedPreferences("skills"));
        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.button_skills_save:
                skills = skillsEdittex.getText().toString();
                updateSkills();
                break;
        }
    }

    private void updateSkills() {
        request = new HttpRequest(getApplicationContext());
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("PUT", String.format("%sme", AppGlobals.BASE_URL));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("skills", skills);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setRequestHeader("Authorization", "Token " +
                AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
        request.send(jsonObject.toString());
        Helpers.showProgressDialog(PersonalSkills.this, "Please wait...");
    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {
        switch (readyState) {
            case HttpRequest.STATE_DONE:
                Helpers.dismissProgressDialog();
                switch (request.getStatus()) {
                    case HttpRequest.ERROR_NETWORK_UNREACHABLE:
                        AppGlobals.alertDialog(PersonalSkills.this, "Failed! ", "please check your internet connection");
                        break;
                    case HttpURLConnection.HTTP_OK:
                        try {
                            JSONObject jsonObject = new JSONObject(request.getResponseText());
                            String mySkills = jsonObject.getString(AppGlobals.KEY_SKILLS);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_SKILLS, mySkills);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        onBackPressed();
                }
        }
    }

    @Override
    public void onError(HttpRequest request, int readyState, short error, Exception exception) {

    }
}
