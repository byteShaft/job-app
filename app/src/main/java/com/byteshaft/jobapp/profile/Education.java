package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Education extends AppCompatActivity implements View.OnClickListener, HttpRequest.OnErrorListener,
        HttpRequest.OnReadyStateChangeListener {


    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;
    private ListView mListView;
    private Button addButton;


    private HttpRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_save_edu);
        backButton = (ImageButton) findViewById(R.id.back_button);
        mListView = (ListView) findViewById(R.id.education_list);
        addButton = (Button) findViewById(R.id.button_add_education);

        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.button_save_edu:
                System.out.println("save");
                break;
            case R.id.button_add_education:
                System.out.println("add button");
                // TODO: 14/06/2017 Add new field on click
                break;
        }
    }

    @Override
    public void onError(HttpRequest request, int readyState, short error, Exception exception) {

    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {

    }

    private void AddEducation(String period, String qualification, String school) {
        request = new HttpRequest(this);
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("POST", String.format("%seducation/ ", AppGlobals.BASE_URL));
        request.send(getEducationData(period, qualification, school));
        Helpers.showProgressDialog(Education.this, "Saving education..");
    }

    private String getEducationData(String period, String qualification, String school) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("period", period);
            jsonObject.put("qualification", qualification);
            jsonObject.put("school", school);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }
}
