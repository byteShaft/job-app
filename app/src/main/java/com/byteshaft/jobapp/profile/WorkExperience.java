package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.adapters.WorkExpAdapter;
import com.byteshaft.jobapp.gettersetters.WorkExp;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class WorkExperience extends AppCompatActivity implements View.OnClickListener,
        HttpRequest.OnReadyStateChangeListener, HttpRequest.OnErrorListener {


    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;
    private ListView mListView;
    private Button addButton;
    private ArrayList<WorkExp> workExperienceArrayList;
    private WorkExpAdapter workExpAdapter;

    private HttpRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_exp_save);
        backButton = (ImageButton) findViewById(R.id.back_button);
        mListView = (ListView) findViewById(R.id.work_exp_list);
        addButton = (Button) findViewById(R.id.button_add_work_experience);
        workExperienceArrayList = new ArrayList<>();

        addButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        getWorkExperienceList();
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
            case R.id.button_add_work_experience:
                System.out.println("add button");
                // TODO: 14/06/2017 Add field for work experience
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


    private void getWorkExperienceList() {
        Helpers.showProgressDialog(WorkExperience.this, "Please wait...");
        HttpRequest requestQualifications = new HttpRequest(getApplicationContext());
        requestQualifications.setOnReadyStateChangeListener(new HttpRequest.OnReadyStateChangeListener() {
            @Override
            public void onReadyStateChange(HttpRequest request, int readyState) {
                switch (readyState) {
                    case HttpRequest.STATE_DONE:
                        Helpers.dismissProgressDialog();
                        switch (request.getStatus()) {
                            case HttpURLConnection.HTTP_OK:
                                try {
                                    JSONArray jsonArray = new JSONArray(request.getResponseText());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        WorkExp workExp = new WorkExp();
                                        workExp.setId(jsonObject.getInt("id"));
                                        workExp.setUserId(jsonObject.getInt("user"));
                                        workExp.setJobTitle(jsonObject.getString("title"));
                                        workExp.setComapnyName(jsonObject.getString("company"));
                                        workExp.setPeriod(jsonObject.getString("period"));
                                        workExperienceArrayList.add(workExp);
                                    }

                                    workExpAdapter = new WorkExpAdapter(WorkExperience.this, workExperienceArrayList);
                                    mListView.setAdapter(workExpAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                }
            }
        });
        requestQualifications.open("GET", String.format("%sexperience/", AppGlobals.BASE_URL));
        requestQualifications.setRequestHeader("Authorization", "Token " +
                AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
        requestQualifications.send();
    }
}
