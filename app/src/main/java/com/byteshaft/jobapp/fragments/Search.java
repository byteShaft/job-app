package com.byteshaft.jobapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.gettersetters.JobDetails;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.jobapp.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Search extends Fragment {

    private View mBaseView;
    private GridView mSearchViewElements;
    private ArrayList<JobDetails> jobsArrayList;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        mSearchViewElements = (GridView) mBaseView.findViewById(R.id.grid_view);
        jobsArrayList = new ArrayList<>();
        getJobsList();
        return mBaseView;
    }

    private void getJobsList() {
        HttpRequest request = new HttpRequest(AppGlobals.getContext());
        Helpers.showProgressDialog(getActivity(), "Please wait...");
        request.setOnReadyStateChangeListener(new HttpRequest.OnReadyStateChangeListener() {
            @Override
            public void onReadyStateChange(HttpRequest request, int readyState) {
                switch (readyState) {
                    case HttpRequest.STATE_DONE:
                        Log.i("MY URLlllll", request.getResponseURL());
                        Helpers.dismissProgressDialog();
                        switch (request.getStatus()) {
                            case HttpURLConnection.HTTP_OK:
                                Log.i("MY Category", request.getResponseText());
                                try {
                                    JSONArray jsonArray = new JSONArray(request.getResponseText());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        JobDetails jobDetails = new JobDetails();
                                        jobDetails.setJobId(jsonObject.getInt("id"));
                                        jobDetails.setJobType(jsonObject.getString("type"));
                                        jobDetails.setJobTitle(jsonObject.getString("title"));
                                        jobDetails.setCreatorName(jsonObject.getString("creator_name"));
                                        jobDetails.setScope(jsonObject.getString("scope"));
                                        jobDetails.setSalary(jsonObject.getString("salary"));
                                        jobDetails.setRequirement(jsonObject.getString("requirement"));
                                        jobDetails.setLocation(jsonObject.getString("location"));
                                        jobDetails.setLocation_name(jsonObject.getString("location_name"));
                                        jobDetails.setDetailDescription(jsonObject.getString("detailed_description"));
                                        jobsArrayList.add(jobDetails);
                                    }
                                    adapter = new ListAdapter(jobsArrayList);
                                    mSearchViewElements.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                }
            }
        });
        request.open("GET", String.format("%sjobs/", AppGlobals.BASE_URL));
        request.send();
    }

    private class ListAdapter extends BaseAdapter {
        private ViewHolder viewHolder;
        private ArrayList<JobDetails> jobDetails;

        public ListAdapter(ArrayList<JobDetails> jobDetails) {
            this.jobDetails = jobDetails;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.delegate_search_fragment, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.companyName = (TextView) convertView.findViewById(R.id.company_description);
                viewHolder.jobTitle = (TextView) convertView.findViewById(R.id.job_title);
                viewHolder.jobLocation = (TextView) convertView.findViewById(R.id.a_job_location);
                viewHolder.saveButton = (ImageView) convertView.findViewById(R.id.fab_save);
//                viewHolder.companyLogo = (CircleImageView) convertView.findViewById(R.id.job_icon);
                viewHolder.salary = (TextView) convertView.findViewById(R.id.a_salary);
                viewHolder.jobCategory = (Button) convertView.findViewById(R.id.job_type);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final JobDetails jobDetail = jobDetails.get(position);
            viewHolder.companyName.setText(jobDetail.getCreatorName());
            viewHolder.jobTitle.setText(jobDetail.getJobTitle());
            viewHolder.jobLocation.setText(jobDetail.getLocation_name());
            viewHolder.salary.setText(jobDetail.getSalary());
            viewHolder.jobCategory.setText(jobDetail.getJobType());
            viewHolder.saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("save button pressed");
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            return jobDetails.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }

    class ViewHolder {
        private ImageView saveButton;
        private TextView companyName;
        private TextView jobTitle;
        private TextView jobLocation;
        private CircleImageView companyLogo;
        private TextView salary;
        private Button jobCategory;
    }
}
