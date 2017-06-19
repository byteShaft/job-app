package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.gettersetters.WorkExp;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class WorkExperience extends AppCompatActivity implements View.OnClickListener {

    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;
    private ListView mListView;
    private Button addButton;
    private ArrayList<WorkExp> workExperienceArrayList;
    private WorkExpAdapter workExpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);
        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_exp_save);
        backButton = (ImageButton) findViewById(R.id.back_button);
        mListView = (ListView) findViewById(R.id.work_exp_list);
        addButton = (Button) findViewById(R.id.button_add_work_experience);
        setSupportActionBar(toolbarTop);
        workExperienceArrayList = new ArrayList<>();

        addButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        workExpAdapter = new WorkExpAdapter(workExperienceArrayList);
        mListView.setAdapter(workExpAdapter);
        getWorkExperienceList();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Item Click");
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Long Click");
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.button_exp_save:
                addWorkExp();
                break;
            case R.id.button_add_work_experience:
                WorkExp workExp = new WorkExp();
                workExp.setComapnyName("");
                workExp.setPeriod("");
                workExp.setJobTitle("");
                workExperienceArrayList.add(workExp);
                workExpAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void addWorkExp() {
        Helpers.showProgressDialog(WorkExperience.this, "Adding...");
        HttpRequest request = new HttpRequest(this);
        request.setOnReadyStateChangeListener(new HttpRequest.OnReadyStateChangeListener() {
            @Override
            public void onReadyStateChange(HttpRequest request, int readyState) {
                switch (readyState) {
                    case HttpRequest.STATE_DONE:
                        Helpers.dismissProgressDialog();
                        switch (request.getStatus()) {
                            case HttpURLConnection.HTTP_CREATED:
                                finish();
                        }
                }

            }
        });
        request.setOnErrorListener(new HttpRequest.OnErrorListener() {
            @Override
            public void onError(HttpRequest request, int readyState, short error, Exception exception) {

            }
        });
        request.open("PUT", String.format("%sme/ ", AppGlobals.BASE_URL));
        request.setRequestHeader("Authorization", "Token " +
                AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
        request.send(getWorkExperienceData());
    }

    private String getWorkExperienceData() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        try {
            for (int i = 0; i < mListView.getCount(); i++){
                JSONObject jsonObject = new JSONObject();
                EditText period = (EditText) mListView.getChildAt(i).findViewById(R.id.et_time_span_work);
                EditText jobTitle = (EditText) mListView.getChildAt(i).findViewById(R.id.et_job_title);
                EditText company = (EditText) mListView.getChildAt(i).findViewById(R.id.et_company);

                jsonObject.put("period", period.getText().toString());
                jsonObject.put("title", jobTitle.getText().toString());
                jsonObject.put("company", company.getText().toString());
                jsonArray.put(jsonObject);
            }
            jsonObject1.put("experience", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject1.toString();
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
                                        workExpAdapter.notifyDataSetChanged();
                                    }
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


    private class WorkExpAdapter extends BaseAdapter {

        private ViewHolder viewHolder;
        private ArrayList<WorkExp> workExperiencesList;
        public WorkExpAdapter(ArrayList<WorkExp> workExperiencesList) {
            this.workExperiencesList = workExperiencesList;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.delegate_experience, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.period = (EditText) convertView.findViewById(R.id.et_time_span_work);
                viewHolder.title = (EditText) convertView.findViewById(R.id.et_job_title);
                viewHolder.company = (EditText) convertView.findViewById(R.id.et_company);
                viewHolder.jobNumber = (TextView) convertView.findViewById(R.id.tv_job_number);
                viewHolder.removeButton = (TextView) convertView.findViewById(R.id.remove_job);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            WorkExp workExperience = workExperiencesList.get(position);
            viewHolder.period.setText(workExperience.getPeriod());
            viewHolder.title.setText(workExperience.getJobTitle());
            viewHolder.company.setText(workExperience.getComapnyName());
            viewHolder.jobNumber.setText("Job # " + (position + 1));
            viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Remove Button job");
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            return workExperiencesList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private class ViewHolder {
            private TextView removeButton;
            private TextView jobNumber;
            private EditText period;
            private EditText title;
            private EditText company;
        }
    }
}
