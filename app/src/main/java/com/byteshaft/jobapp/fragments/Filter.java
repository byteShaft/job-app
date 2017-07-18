package com.byteshaft.jobapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byteshaft.jobapp.MainActivity;
import com.byteshaft.jobapp.R;
import com.byteshaft.jobapp.accounts.AccountManager;
import com.byteshaft.jobapp.activities.FilterLocation;
import com.byteshaft.jobapp.profile.ProfileSettings;


public class Filter extends Fragment implements View.OnClickListener{

    private View mBaseView;

    private ImageButton mBackButton;
    private TextView mResetTextView;
    private RelativeLayout mLocationLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_filter, container, false);
        mBackButton = (ImageButton) mBaseView.findViewById(R.id.back_button);
        mResetTextView = (TextView) mBaseView.findViewById(R.id.reset_text_view);
        mLocationLayout = (RelativeLayout) mBaseView.findViewById(R.id.location_layout);

        mBackButton.setOnClickListener(this);
        mResetTextView.setOnClickListener(this);
        mLocationLayout.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                break;
            case R.id.reset_text_view:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Confirmation");
                alertDialogBuilder.setMessage("Do you really want to reset?")
                        .setCancelable(false).setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.location_layout:
                startActivity(new Intent(getActivity(), FilterLocation.class));
                break;
        }
    }
}
