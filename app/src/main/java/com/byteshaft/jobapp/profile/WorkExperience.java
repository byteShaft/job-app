package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class WorkExperience extends AppCompatActivity implements View.OnClickListener,
        HttpRequest.OnReadyStateChangeListener, HttpRequest.OnErrorListener{



    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;

    private HttpRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_exp_save);
        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.button_exp_save:
                System.out.println("save");
                break;
        }
    }

    private void AddEducation(String company, String period, String title) {
        request = new HttpRequest(this);
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("POST", String.format("%sexperience/ ", AppGlobals.BASE_URL));
        request.send(getWorkExperienceData(company, period, title));
        Helpers.showProgressDialog(WorkExperience.this, "Saving Experiences..");
    }

    private String getWorkExperienceData(String company, String period, String title) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("company", company);
            jsonObject.put("period", period);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }

    @Override
    public void onError(HttpRequest request, int readyState, short error, Exception exception) {

    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {

    }
}
