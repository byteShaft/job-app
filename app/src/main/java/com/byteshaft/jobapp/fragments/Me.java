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
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.profile.Education;
import com.byteshaft.jobapp.profile.PersonalSkills;
import com.byteshaft.jobapp.profile.ProfileSettings;
import com.byteshaft.jobapp.profile.WorkExperience;
import com.byteshaft.jobapp.utils.AppGlobals;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shahid on 28/05/2017.
 */

public class Me extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private Toolbar toolbarTop;
    private ImageButton settingsButton;
    private TextView title;
    private CircleImageView jobAppliedButton;
    private CircleImageView jobSavedButton;
    private CircleImageView jobResumeButton;

    private TextView workExperienceEditTextView;
    private TextView educationEditTextView;
    private TextView personalSkillsEditTextView;

    private TextView skillsTextViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_me, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbarTop = (Toolbar) mBaseView.findViewById(R.id.profile_toolbar);
        settingsButton = (ImageButton) toolbarTop.findViewById(R.id.button_settings);
        jobAppliedButton = (CircleImageView) mBaseView.findViewById(R.id.job_applied);
        jobSavedButton = (CircleImageView) mBaseView.findViewById(R.id.job_saved);
        jobResumeButton = (CircleImageView) mBaseView.findViewById(R.id.resume);

        skillsTextViews = (TextView) mBaseView.findViewById(R.id.skills_text_view);

        title = (TextView) toolbarTop.findViewById(R.id.profile_title);
        workExperienceEditTextView = (TextView) mBaseView.findViewById(R.id.work_experience_edit_text_view);
        educationEditTextView = (TextView) mBaseView.findViewById(R.id.education_edit_text_view);
        personalSkillsEditTextView = (TextView) mBaseView.findViewById(R.id.personal_skills_edit_text_view);
        activity.setSupportActionBar(toolbarTop);
        settingsButton.setOnClickListener(this);
        workExperienceEditTextView.setOnClickListener(this);
        educationEditTextView.setOnClickListener(this);
        personalSkillsEditTextView.setOnClickListener(this);

        jobAppliedButton.setOnClickListener(this);
        jobSavedButton.setOnClickListener(this);
        jobResumeButton.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onResume() {
        super.onResume();
        skillsTextViews.setText(AppGlobals.getStringFromSharedPreferences("skills"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_settings:
                startActivity(new Intent(getActivity(), ProfileSettings.class));
                break;
            case R.id.work_experience_edit_text_view:
                startActivity(new Intent(getActivity(), WorkExperience.class));
                break;
            case R.id.education_edit_text_view:
                startActivity(new Intent(getActivity(), Education.class));
                break;
            case R.id.personal_skills_edit_text_view:
                startActivity(new Intent(getActivity(), PersonalSkills.class));
                break;

            case R.id.job_applied:
                loadFragment(new JobApplied());
                break;
            case R.id.job_saved:
                loadFragment(new JobSaved());
                break;
//            case R.id.job_resume:
//                break;

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