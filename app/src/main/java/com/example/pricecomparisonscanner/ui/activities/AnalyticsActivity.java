package com.example.pricecomparisonscanner.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.ui.BarcodeActivity;
import com.example.pricecomparisonscanner.ui.helpers.ExpandableListAdapter;
import com.example.pricecomparisonscanner.ui.helpers.HelpDataDump;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AnalyticsActivity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    double[] stats;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.resultsTextView1);
        textView2 = findViewById(R.id.resultsTextView2);
        textView3 = findViewById(R.id.resultsTextView3);
        textView4 = findViewById(R.id.resultsTextView4);

        stats = BarcodeActivity.getActivityInstance().getStats();
        textView.setText("Analytics for your last scan");
        textView1.setText("Mean Price: " + formatDecimal(stats[0]));
        textView2.setText("Median Price: " + formatDecimal(stats[1]));
        textView3.setText("Lowest Price: " + formatDecimal(stats[4]));
        textView4.setText("Variance: " + formatDecimal(stats[3]));

    }

    public String formatDecimal(double n) {
        float number = (float) n;
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return "$" + String.format("%10.0f", number); // sdb
        } else {
            return "$" + String.format("%10.2f", number); // dj_segfault
        }
    }

    protected int getNavigationDrawerID() {
        return R.id.nav_analytics;
    }
}
