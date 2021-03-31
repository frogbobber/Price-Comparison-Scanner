package com.example.pricecomparisonscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.resultsTextView);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }

    public void ScanButton(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }

    public void ViewAnalytics(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

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
                            //if run out of scams comment out here
                            URL url = new URL("https://api.upcitemdb.com/prod/trial/lookup?upc=" + intentResult.getContents());
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET"); // testtest
                            textView.setText("Connecting");
                            conn.connect();

                            int responseCode = conn.getResponseCode();
                            if (responseCode != 200) {
                                textView.setText(responseCode);
                                throw new RuntimeException("yuh oh, bad response");
                            } else {
                                Scanner scanner = new Scanner(url.openStream());
                                StringBuilder builder = new StringBuilder();
                                while (scanner.hasNext()) {
                                    builder.append(scanner.nextLine());
                                }

                                inline = builder.toString();

                                scanner.close();
                            }

                            textView.setText("retrived from upcitemdb");

                            textView.setText("Got Response: ");

                            JSONObject jsonObject = new JSONObject(inline);
                            JSONArray jsonArray = jsonObject.getJSONArray("items").getJSONObject(0).getJSONArray("offers");
                            Double price1 = jsonArray.getJSONObject(0).getDouble("price");


                            //textView.setText(price1 + "");
                            String name = jsonArray.getJSONObject(0).getString("title").replaceAll(" ", "+");
//                            AllProductInformation allProductInformation = WebScraper.getProductInformation(name);
//                            textView.setText("\n\n" + name  + "\n\n" + allProductInformation + "");
//                            System.out.println(allProductInformation + "");
                            //to here
                            //String name = "MONOPOLY+Game";//add this ->
                            AllProductInformation info = WebScraper.getProductInformation(name);
                            info.setUpciteProducts(jsonObject);//and this one too
                            info = new AllProductInformation(4, info);
                            
                            // textView.setText("\n\n" + name  + "\n\n" + info + ""); //Old Method
                            
                            StringBuilder outputBuilder = new StringBuilder();
                            
                            outputBuilder.append("Broad Sweep Database: \n");

                            ArrayList<ProductInformation> products = info.getUpciteProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder.append(
                                        products.get(i).getName() + "\n" +
                                                products.get(i).getPrice() + "\n" +
                                                products.get(i).getUrl() + "\n\n"
                                );
                            }
                            products = info.getWalmartProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder.append(
                                        products.get(i).getName() + "\n" +
                                                products.get(i).getPrice() + "\n" +
                                                products.get(i).getUrl() + "\n\n"
                                );
                            }
                            products = info.getAmazonProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder.append(
                                        products.get(i).getName() + "\n" +
                                                products.get(i).getPrice() + "\n" +
                                                products.get(i).getUrl() + "\n\n"
                                );
                            }
                            products = info.getTargetProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder.append(
                                        products.get(i).getName() + "\n" +
                                                products.get(i).getPrice() + "\n" +
                                                products.get(i).getUrl() + "\n\n"
                                );
                            }
                            
                            textView.setText(outputBuilder.toString());
                            
                            System.out.println("\n\nnew info - \n" + info + "\n - end info\n\n");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

            }
        }
    }
}