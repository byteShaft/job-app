
package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by husnain on 6/1/17.
 */

public class ProfileSettings extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.byteshaft.jobapp.R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");
    }

}


