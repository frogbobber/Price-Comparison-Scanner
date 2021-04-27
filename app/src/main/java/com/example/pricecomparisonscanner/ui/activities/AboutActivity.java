package com.example.pricecomparisonscanner.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.ui.helpers.ExpandableListAdapter;
import com.example.pricecomparisonscanner.ui.helpers.AboutDataDump;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ExpandableListAdapter expandableListAdapter;
        AboutDataDump aboutDataDump = new AboutDataDump(this);

        ExpandableListView generalExpandableListView = (ExpandableListView) findViewById(R.id.generalExpandableListView);

        LinkedHashMap<String, List<String>> expandableListDetail = aboutDataDump.getDataGeneral();
        List<String> expandableListTitleGeneral = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitleGeneral, expandableListDetail);
        generalExpandableListView.setAdapter(expandableListAdapter);

        overridePendingTransition(0, 0);

    }
    protected int getNavigationDrawerID() {
        return R.id.nav_about;
    }
}
