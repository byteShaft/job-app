package com.byteshaft.jobapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.utils.AppGlobals;
import com.byteshaft.requests.HttpRequest;

import java.net.HttpURLConnection;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobsList extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private TextView title;
    private Toolbar toolbarTop;
    private ImageButton backButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.jobs_list_fragment, container, false);
        String categoryValue = getArguments().getString("category");
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbarTop = (Toolbar) mBaseView.findViewById(R.id.my_toolbar);
        title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        backButton = (ImageButton) toolbarTop.findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
        title.setText(categoryValue.toUpperCase());
        getJobsList(categoryValue);
        return mBaseView;
    }

    private void getJobsList(String category) {
        HttpRequest request = new HttpRequest(AppGlobals.getContext());
        request.setOnReadyStateChangeListener(new HttpRequest.OnReadyStateChangeListener() {
            @Override
            public void onReadyStateChange(HttpRequest request, int readyState) {
                switch (readyState) {
                    case HttpRequest.STATE_DONE:
                        switch (request.getStatus()) {
                            case HttpURLConnection.HTTP_OK:
                                Log.i("MY Category", request.getResponseText());
                        }
                }
            }
        });
        request.open("GET", String.format("%sjobs/?category=%s", AppGlobals.BASE_URL, category));
        request.send();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                break;
        }
    }

    private class JobListAdapter extends BaseAdapter {



        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    class ViewHolder {
        private TextView companyName;
        private TextView jobTitle;
        private TextView jobLocation;
        private CircleImageView companyLogo;
        private TextView salary;
        private TextView jobCategory;
    }
}
