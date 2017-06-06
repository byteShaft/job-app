package com.byteshaft.jobapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.activities.FullTime;
import com.byteshaft.jobapp.activities.Internship;
import com.byteshaft.jobapp.activities.MessagesActivity;
import com.byteshaft.jobapp.activities.PartTime;
import com.byteshaft.jobapp.activities.QRcodeActivity;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


public class Home extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private BannerSlider bannerSlider;
    private Button partTime;
    private Button fullTime;
    private Button internShip;
    private Toolbar toolbarTop;
    private ImageButton barcodeButton;
    private ImageButton messageButton;
    private TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_home, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbarTop = (Toolbar) mBaseView.findViewById(R.id.main_toolbar);
        barcodeButton = (ImageButton) mBaseView.findViewById(R.id.button_barcode);
        messageButton = (ImageButton) mBaseView.findViewById(R.id.button_message);
        bannerSlider = (BannerSlider) mBaseView.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));

        title = (TextView) mBaseView.findViewById(R.id.toolbar_title);
        title.setText(getActivity().getTitle());

        partTime = (Button) mBaseView.findViewById(R.id.button_part_time);
        fullTime = (Button) mBaseView.findViewById(R.id.button_full_time);
        internShip = (Button) mBaseView.findViewById(R.id.button_internship);
        activity.setSupportActionBar(toolbarTop);
        partTime.setOnClickListener(this);
        fullTime.setOnClickListener(this);
        internShip.setOnClickListener(this);
        barcodeButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_part_time:
                loadFragment(new PartTime());
                break;
            case R.id.button_full_time:
                loadFragment(new FullTime());
                break;
            case R.id.button_internship:
                loadFragment(new Internship());
                break;
            case R.id.button_barcode:
                startActivity(new Intent(getActivity(), QRcodeActivity.class));
                break;
            case R.id.button_message:
                startActivity(new Intent(getActivity(), MessagesActivity.class));
        }
    }

    public void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fragment_container, fragment, backStateName);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Log.i("TAG", backStateName);
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }
}
