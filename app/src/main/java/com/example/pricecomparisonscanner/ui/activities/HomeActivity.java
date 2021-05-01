package com.example.pricecomparisonscanner.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.ui.helpers.ExpandableListAdapter;
import com.example.pricecomparisonscanner.ui.helpers.HelpDataDump;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HomeActivity extends com.example.pricecomparisonscanner.ui.helpers.BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }

    @Override
    protected int getNavigationDrawerID() {
        return R.id.nav_home;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
