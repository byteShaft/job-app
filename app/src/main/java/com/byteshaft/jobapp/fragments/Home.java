package com.byteshaft.jobapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.byteshaft.jobapp.R;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


public class Home extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private BannerSlider bannerSlider;
    private Button partTime;
    private Button fullTime;
    private Button internShip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_home, container, false);

        bannerSlider = (BannerSlider) mBaseView.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.slide_imge));

        partTime = (Button) mBaseView.findViewById(R.id.button_part_time);
        fullTime = (Button) mBaseView.findViewById(R.id.button_full_time);
        internShip = (Button) mBaseView.findViewById(R.id.button_internship);

        partTime.setOnClickListener(this);
        fullTime.setOnClickListener(this);
        internShip.setOnClickListener(this);

        return mBaseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_part_time:
                break;
            case R.id.button_full_time:
                break;
            case R.id.button_internship:
                break;
        }
    }
}
