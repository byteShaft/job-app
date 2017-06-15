package com.byteshaft.jobapp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.gettersetters.Qualification;

import java.util.ArrayList;

/**
 * Created by shahid on 15/06/2017.
 */

public class QualificationAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private ArrayList<Qualification> qualificationsList;
    private Activity activity;

    public QualificationAdapter(Activity activity, ArrayList<Qualification> qualificationsList) {
        this.activity = activity;
        this.qualificationsList = qualificationsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.delegate_education, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.period = (EditText) convertView.findViewById(R.id.et_time_span);
            viewHolder.qualification = (EditText) convertView.findViewById(R.id.et_qualification);
            viewHolder.school = (EditText) convertView.findViewById(R.id.et_school);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Qualification qualification = qualificationsList.get(position);
        viewHolder.period.setText(qualification.getPeriod());
        viewHolder.qualification.setText(qualification.getQualification());
        viewHolder.school.setText(qualification.getSchool());
        return convertView;
    }

    @Override
    public int getCount() {
        return qualificationsList.size();
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
        private EditText qualification;
        private EditText school;
    }
}
