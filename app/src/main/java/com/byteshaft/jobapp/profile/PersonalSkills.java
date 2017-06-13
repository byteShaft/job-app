package com.byteshaft.jobapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;


public class PersonalSkills extends AppCompatActivity implements View.OnClickListener {


    private TextView buttonSave;
    private Toolbar toolbarTop;
    private ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_skills);

        toolbarTop = (Toolbar) findViewById(R.id.add_education_toolbar);
        buttonSave = (TextView) findViewById(R.id.button_skills_save);
        backButton = (ImageButton) findViewById(R.id.back_button);

        backButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.button_skills_save:
                System.out.println("Save skill");
                break;
        }
    }
}
