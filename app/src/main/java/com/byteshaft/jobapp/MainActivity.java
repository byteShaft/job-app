package com.byteshaft.jobapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.byteshaft.jobapp.fragments.Home;
import com.byteshaft.jobapp.fragments.Me;
import com.byteshaft.jobapp.fragments.Search;


public class MainActivity extends AppCompatActivity {

    public static MainActivity sInstance;

    public static MainActivity getInstance() {
        return sInstance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.enter, R.anim.exit);
        super.onCreate(savedInstanceState);
        sInstance = this;
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadThisFragment(new Home(), "", "");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadThisFragment(new Home(), "", "");
                    return true;
                case R.id.navigation_search:
                    loadThisFragment(new Search(), "", "");
                    return true;
                case R.id.navigation_me:
                    loadThisFragment(new Me(), "", "");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadThisFragment(Fragment fragment, String category, String type) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        tx.replace(R.id.fragment_container, fragment);
        tx.commit();
    }
}
