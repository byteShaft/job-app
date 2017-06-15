package com.byteshaft.jobapp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.gettersetters.WorkExp;

import java.util.ArrayList;

public class WorkExpAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private ArrayList<WorkExp> workExperiencesList;
    private Activity activity;

    public WorkExpAdapter(Activity activity, ArrayList<WorkExp> workExperiencesList) {
        this.activity = activity;
        this.workExperiencesList = workExperiencesList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.delegate_experience, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.period = (EditText) convertView.findViewById(R.id.et_time_span_work);
            viewHolder.title = (EditText) convertView.findViewById(R.id.et_job_title);
            viewHolder.company = (EditText) convertView.findViewById(R.id.et_company);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WorkExp workExperience = workExperiencesList.get(position);
        viewHolder.period.setText(workExperience.getPeriod());
        viewHolder.title.setText(workExperience.getJobTitle());
        viewHolder.company.setText(workExperience.getComapnyName());
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
        private EditText period;
        private EditText title;
        private EditText company;
    }
}

