package com.example.pricecomparisonscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }

    public void ScanButton(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan(); //mayonaise
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                textView.setText("Cancelled");
            } else {
                textView.setText(intentResult.getContents());

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String inline = "";
                            System.out.println("lololol");
                            URL url = new URL("https://api.upcitemdb.com/prod/trial/lookup?upc=" + intentResult.getContents());
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET"); // testtest
                            conn.connect();

                            int responseCode = conn.getResponseCode();
                            if (responseCode != 200) {
                                textView.setText(responseCode);
                                throw new RuntimeException("yuh oh, bad response");
                            } else {
                                Scanner scanner = new Scanner(url.openStream());

                                while (scanner.hasNext()) {
                                    inline += scanner.nextLine();
                                }

                                scanner.close();
                            }

                            JSONObject jsonObject = new JSONObject(inline);
                            JSONArray jsonArray = jsonObject.getJSONArray("items").getJSONObject(0).getJSONArray("offers");
                            Double price1 = jsonArray.getJSONObject(0).getDouble("price");

                            //textView.setText(price1 + "");
                            String name = jsonArray.getJSONObject(0).getString("title").replaceAll(" ", "+");
                            textView.setText("\n\n" + name  + "\n\n" + WebScraper.getProductInformation(name) + "");

                        } catch (Exception e) {
                        }
                    }
                });

                thread.start();

            }
        }
    }
}