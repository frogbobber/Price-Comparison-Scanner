package com.example.pricecomparisonscanner.ui.helpers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.ui.BarcodeActivity;
import com.example.pricecomparisonscanner.ui.activities.HomeActivity;
import com.example.pricecomparisonscanner.ui.activities.ResultsActivity;
import com.example.pricecomparisonscanner.ui.activities.ScannerActivity;
import com.example.pricecomparisonscanner.ui.activities.TutorialActivity;
import com.example.pricecomparisonscanner.ui.activities.AnalyticsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
/*
import com.example.pricecomparisonscanner.ui.HistoryActivity;
import com.qr.code.generate.ui.activities.SettingsActivity;
import com.qr.code.generate.ui.activities.generator.QrGeneratorOverviewActivity;*/

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final int NAVDRAWER_LAUNCH_DELAY = 250;

    public static final int MAIN_CONTENT_FADEOUT_DURATION = 150;
    public static final int MAIN_CONTENT_FADEIN_DURATION = 250;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    // Helper
    private Handler mHandler;
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mHandler = new Handler();

        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected abstract int getNavigationDrawerID();

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        return goToNavigationItem(itemId);
    }

    protected boolean goToNavigationItem(final int itemId) {

        if(itemId == getNavigationDrawerID()) {
            // just close drawer because we are already in this activity
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        // delay transition so the drawer can close
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callDrawerItem(itemId);
            }
        }, NAVDRAWER_LAUNCH_DELAY);

        mDrawerLayout.closeDrawer(GravityCompat.START);

        selectNavigationItem(itemId);

        // fade out the active activity
        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.animate().alpha(0).setDuration(MAIN_CONTENT_FADEOUT_DURATION);
        }
        return true;
    }

    // set active navigation item
    private void selectNavigationItem(int itemId) {
        for(int i = 0 ; i < mNavigationView.getMenu().size(); i++) {
            boolean b = itemId == mNavigationView.getMenu().getItem(i).getItemId();
            mNavigationView.getMenu().getItem(i).setChecked(b);
        }
    }

    private void createBackStack(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(this);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void callDrawerItem(final int itemId) {
        Intent intent;

        switch(itemId) {
            case R.id.nav_barcode:
                intent = new Intent(this, BarcodeActivity.class);
                createBackStack(intent);
                break;
            case R.id.nav_scan:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setCaptureActivity(ScannerActivity.class);
                integrator.setBeepEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
                break;
            case R.id.nav_home:
                intent = new Intent(this, HomeActivity.class);
                createBackStack(intent);
                break;
            case R.id.nav_tutorial:
                intent = new Intent(this, TutorialActivity.class);
                intent.setAction(TutorialActivity.ACTION_SHOW_ANYWAYS);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                createBackStack(intent);
                break;
            case R.id.nav_analytics:
                intent = new Intent(this, AnalyticsActivity.class);
                createBackStack(intent);
                break;
            case R.id.nav_results:
                intent = new Intent(this, ResultsActivity.class);
                createBackStack(intent);
                break;
            /*case R.id.nav_generate:
                intent = new Intent(this, QrGeneratorOverviewActivity.class);
                createBackStack(intent);
                break;
            case R.id.nav_history:
                intent = new Intent(this, HistoryActivity.class);
                createBackStack(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                createBackStack(intent);
                break;*/
            default:
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        selectNavigationItem(getNavigationDrawerID());

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        }
    }


}