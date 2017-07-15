package com.byteshaft.jobapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byteshaft.jobapp.MainActivity;
import com.byteshaft.jobapp.R;

/**
 * Created by shahid on 13/06/2017.
 */

public class FullTime extends Fragment implements View.OnClickListener {


    private View mBaseView;
    private TextView title;
    private Toolbar toolbarTop;
    private ImageButton backButton;
    // buttons
    private LinearLayout healthNfitness;
    private LinearLayout insurance;
    private LinearLayout it;
    private LinearLayout media;
    private LinearLayout science;
    private LinearLayout legalProfessional;
    private LinearLayout nursery;
    private LinearLayout manufacturing;
    private LinearLayout property;
    private LinearLayout sales;
    private LinearLayout transportation;
    private LinearLayout hospitality;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.activity_full_time, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        toolbarTop = (Toolbar) mBaseView.findViewById(R.id.my_toolbar);
        title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        backButton = (ImageButton) toolbarTop.findViewById(R.id.back_button);
        // buttons
        healthNfitness = (LinearLayout) mBaseView.findViewById(R.id.full_health_fitness);
        insurance = (LinearLayout) mBaseView.findViewById(R.id.full_insurance);
        it = (LinearLayout) mBaseView.findViewById(R.id.full_it);
        media = (LinearLayout) mBaseView.findViewById(R.id.full_media);
        science = (LinearLayout) mBaseView.findViewById(R.id.full_science_search);
        legalProfessional = (LinearLayout) mBaseView.findViewById(R.id.full_legal_professional);
        nursery = (LinearLayout) mBaseView.findViewById(R.id.full_nursery);
        manufacturing = (LinearLayout) mBaseView.findViewById(R.id.full_manufacturing);
        property = (LinearLayout) mBaseView.findViewById(R.id.full_property);
        sales = (LinearLayout) mBaseView.findViewById(R.id.full_sales);
        transportation = (LinearLayout) mBaseView.findViewById(R.id.full_transportation);
        hospitality = (LinearLayout) mBaseView.findViewById(R.id.full_hospitality);

        healthNfitness.setOnClickListener(this);
        insurance.setOnClickListener(this);
        it.setOnClickListener(this);
        media.setOnClickListener(this);
        science.setOnClickListener(this);
        legalProfessional.setOnClickListener(this);
        nursery.setOnClickListener(this);
        manufacturing.setOnClickListener(this);
        property.setOnClickListener(this);
        sales.setOnClickListener(this);
        transportation.setOnClickListener(this);
        hospitality.setOnClickListener(this);

        backButton.setOnClickListener(this);
        title.setText(R.string.full_time_title);
        activity.setSupportActionBar(toolbarTop);
        return mBaseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                break;
            // buttons
            case R.id.full_health_fitness:

                System.out.println("OK");
                break;
            case R.id.full_insurance:
                System.out.println("OK");
                break;
            case R.id.full_it:
                MainActivity.getInstance().loadThisFragment(new JobsList(), "it");
                System.out.println("OK");
                break;
            case R.id.full_media:
                System.out.println("OK");
                break;
            case R.id.full_science_search:
                System.out.println("OK");
                break;
            case R.id.full_legal_professional:
                System.out.println("OK");
                break;
            case R.id.full_nursery:
                System.out.println("OK");
                break;
            case R.id.full_manufacturing:
                System.out.println("OK");
                break;
            case R.id.full_property:
                System.out.println("OK");
                break;
            case R.id.full_sales:
                System.out.println("OK");
                break;
            case R.id.full_transportation:
                System.out.println("OK");
                break;
            case R.id.full_hospitality:
                System.out.println("OK");
                break;
        }
    }
}