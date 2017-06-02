package com.byteshaft.jobapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.profile.profileSettings;
import com.byteshaft.jobapp.R;

/**
 * Created by shahid on 28/05/2017.
 */

public class Me extends Fragment implements View.OnClickListener{

    private View mBaseView;
    private Toolbar toolbarTop;
    private ImageButton settingsButton;
    private TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_me, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbarTop = (Toolbar) mBaseView.findViewById(R.id.profile_toolbar);
        settingsButton = (ImageButton) toolbarTop.findViewById(R.id.button_settings);
        title = (TextView) toolbarTop.findViewById(R.id.profile_title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Profile");
        activity.setSupportActionBar(toolbarTop);
        settingsButton.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_settings:
                startActivity(new Intent(getActivity(), profileSettings.class));
                System.out.println("ok kr k shadi krwao");
        }

    }
}