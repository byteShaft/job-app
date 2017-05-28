package com.byteshaft.jobapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byteshaft.jobapp.R;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by shahid on 28/05/2017.
 */

public class Home extends Fragment {

    private View mBaseView;
    private BannerSlider bannerSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_home, container, false);

        bannerSlider = (BannerSlider) mBaseView.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new DrawableBanner(R.mipmap.gold_circle));
        bannerSlider.addBanner(new DrawableBanner(R.mipmap.gold_circle));
        bannerSlider.addBanner(new DrawableBanner(R.mipmap.gold_circle));

        return mBaseView;
    }
}
