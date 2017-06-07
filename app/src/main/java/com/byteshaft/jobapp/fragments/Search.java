package com.byteshaft.jobapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.byteshaft.jobapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shahid on 28/05/2017.
 */

public class Search extends Fragment {

    private View mBaseView;
    private GridView mSearchViewElements;
    private ArrayList<String[]> cardViewList;
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        mSearchViewElements = (GridView) mBaseView.findViewById(R.id.grid_view);
        cardViewList = new ArrayList<>();

        cardViewList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        cardViewList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        cardViewList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        cardViewList.add(new String[]{"", "Bilal", "Hello world", "12:00"});
        adapter = new Adapter(getActivity(), cardViewList);
        mSearchViewElements.setAdapter(adapter);
        return mBaseView;
    }

    private class Adapter extends ArrayAdapter<String> {

        private ArrayList<String[]> cardViewList;
        private ViewHolder viewHolder;

        public Adapter(Context context, ArrayList<String[]> messagesList) {
            super(context, R.layout.delegate_search_fragment);
            this.cardViewList = messagesList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.delegate_search_fragment,
                        parent, false);
                viewHolder = new ViewHolder();

                viewHolder.companyImage = (CircleImageView) convertView.findViewById(R.id.job_icon);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // se
            return convertView;
        }

        @Override
        public int getCount() {
            return cardViewList.size();
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
