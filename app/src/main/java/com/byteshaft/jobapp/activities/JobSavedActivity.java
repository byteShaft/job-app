package com.byteshaft.jobapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobSavedActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String[]> jobsArrayList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_saved);
        mListView = (ListView) findViewById(R.id.jobs_list);
        jobsArrayList = new ArrayList<>();

        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        jobsArrayList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        adapter = new Adapter(getApplicationContext(), jobsArrayList);
        mListView.setAdapter(adapter);
    }


    private class Adapter extends ArrayAdapter<String> {

        private ArrayList<String[]> jobsList;
        private ViewHolder viewHolder;

        public Adapter(Context context, ArrayList<String[]> messagesList) {
            super(context, R.layout.delegate_jobs_list);
            this.jobsList = messagesList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.delegate_jobs_list,
                        parent, false);
                viewHolder = new ViewHolder();

                viewHolder.companyImage = (CircleImageView) convertView.findViewById(R.id.job_icon);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // set
            return convertView;
        }

        @Override
        public int getCount() {
            return jobsList.size();
        }
    }

    private class ViewHolder {
        private TextView companyTitle;
        private TextView jobTitle;
        private TextView jobLocation;
        private TextView sallery;
        private TextView companyName;
        private Button partTimeButton;
        private Button jobSaveButton;
        private Button jobApplyButton;
        private CircleImageView companyImage;
    }
}
