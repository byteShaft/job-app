package com.byteshaft.jobapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView companyName;
    private TextView jobDescription;
    private TextView jobRequirement;
    private TextView createdAt;
    private TextView jobLocation;
    private TextView phoneNumber;
    private TextView website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        String id = getIntent().getStringExtra("id");

        companyName = (TextView) findViewById(R.id.tv_company_name);
        jobDescription = (TextView) findViewById(R.id.tv_job_description);
        jobLocation = (TextView) findViewById(R.id.tv_location);
        jobRequirement = (TextView)findViewById(R.id.tv_job_requirement);
        phoneNumber = (TextView) findViewById(R.id.tv_phone);
        createdAt = (TextView) findViewById(R.id.date);

        int jobId = Integer.parseInt(id);
        getJobDetails(jobId);
//        getJobDetails(id);
    }

    private void getJobDetails(int id) {
        HttpRequest request = new HttpRequest(getApplicationContext());
        Helpers.showProgressDialog(JobDetailsActivity.this, "Please wait...");
        request.setOnReadyStateChangeListener(new HttpRequest.OnReadyStateChangeListener() {
            @Override
            public void onReadyStateChange(HttpRequest request, int readyState) {
                switch (readyState) {
                    case HttpRequest.STATE_DONE:
                        Helpers.dismissProgressDialog();
                        switch (request.getStatus()) {
                            case HttpURLConnection.HTTP_OK:
                                Log.e("TTtttttttt", request.getResponseText());
                                try {
                                    JSONObject jsonObject = new JSONObject(request.getResponseText());
                                    String location = jsonObject.getString("location");
                                    String date = jsonObject.getString("created_at");
                                    String scope = jsonObject.getString("scope");
                                    String title = jsonObject.getString("title");
                                    String requirement = jsonObject.getString("requirement");
                                    String description = jsonObject.getString("detailed_description");

                                    jobLocation.setText(location);
                                    createdAt.setText(date);
                                    companyName.setText(title);
                                    jobRequirement.setText(requirement);
                                    jobDescription.setText(description);

                                    System.out.println(location + "  " + title);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                }
            }
        });
        request.open("GET", String.format("%sjobs/%s", AppGlobals.BASE_URL, id));
        request.send();
    }
}
