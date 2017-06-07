package com.byteshaft.jobapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byteshaft.jobapp.R;

/**
 * Created by husnain on 6/7/17.
 */

public class Filter extends Fragment {

    private View mBaseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_filter, container, false);
        return mBaseView;
    }
}
