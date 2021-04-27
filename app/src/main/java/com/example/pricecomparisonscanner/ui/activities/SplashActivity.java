package com.example.pricecomparisonscanner.ui.activities;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.helpers.PrefManager;

public class SplashActivity extends AppCompatActivity {

    private static final int code=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splashactivity);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //PrefManager firstStartPref = new PrefManager(SplashActivity.this);

                Intent mainIntent;

                mainIntent = new Intent(SplashActivity.this, ScannerActivity.class);

                /*if(firstStartPref.isFirstTimeLaunch()) {
                    mainIntent = new Intent(SplashActivity.this, TutorialActivity.class);
                } else {
                    mainIntent = new Intent(SplashActivity.this, ScannerActivity.class);
                }*/

                startActivity(mainIntent);
                finish();
            }
        }
        ,code);



    }
}
