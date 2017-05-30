package com.byteshaft.jobapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.jobapp.fragments.Home;
import com.byteshaft.jobapp.fragments.Me;
import com.byteshaft.jobapp.fragments.Search;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbarTop;
    private ImageButton barcodeButton;
    private ImageButton messageButton;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }
        toolbarTop = (Toolbar) findViewById(R.id.my_toolbar);
        title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        barcodeButton = (ImageButton) toolbarTop.findViewById(R.id.button_barcode);
        messageButton = (ImageButton) toolbarTop.findViewById(R.id.button_message);
        setSupportActionBar(toolbarTop);
        title.setText(toolbarTop.getTitle());
        barcodeButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadThisFragment(new Home());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadThisFragment(new Home());
                    return true;
                case R.id.navigation_search:
                    loadThisFragment(new Search());
                    return true;
                case R.id.navigation_me:
                    loadThisFragment(new Me());
                    return true;
            }
            return false;
        }

    };


    public void loadThisFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_container, fragment);
        tx.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_barcode:
                System.out.println("barcode");
                break;
            case R.id.button_message:
                System.out.println("message");
                break;
        }
    }
}
