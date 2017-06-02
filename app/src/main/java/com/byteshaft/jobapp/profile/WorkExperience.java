package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.byteshaft.jobapp.R;

/**
 * Created by husnain on 6/2/17.
 */

public class WorkExperience extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_work_experience);

    }
}
