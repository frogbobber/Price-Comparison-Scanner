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

import com.example.pricecomparisonscanner.analysis.DataProcessor;
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
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private Button button;
    private AllProductInformation allProductInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.resultsTextView1);
        textView2 = findViewById(R.id.resultsTextView2);
        textView3 = findViewById(R.id.resultsTextView3);
        textView4 = findViewById(R.id.resultsTextView4);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }

    public void ScanButton(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }

    public void ViewAnalytics(View view) {

        try {
            if (allProductInformation != null) {
                DataProcessor dp = new DataProcessor(allProductInformation);
                textView.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");

                StringBuilder builder = new StringBuilder();

                builder.append("   Price Analytics: \n\n");
                builder.append("Best Value Product: \n" + dp.getBestListing().getName() + " " + dp.getBestListing().getPrice() + "\nat: " + dp.getBestListing().getUrl() + "\n\n");
                builder.append("Average Price: " + dp.getMean() + "\n");
                builder.append("Median Price: " + dp.getMedian() + "\n");
                builder.append("Variance: " + dp.getVariance() + "\n");

                textView.setText(builder.toString());
            } else {
                textView.setText("   Please scan a barcode first.");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        textView.setText("");
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");

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
                            //to here */
                            //String name = "MONOPOLY+Game";//add this ->
                            allProductInformation = WebScraper.getProductInformation(name);
                            AllProductInformation info = allProductInformation;
                            info.setUpciteProducts(jsonObject);//and this one too
                            info = new AllProductInformation(4, info);
                            
                            // textView.setText("\n\n" + name  + "\n\n" + info + ""); //Old Method
                            
                            StringBuilder outputBuilder = new StringBuilder();
                            StringBuilder outputBuilder2 = new StringBuilder();
                            StringBuilder outputBuilder3 = new StringBuilder();
                            StringBuilder outputBuilder4 = new StringBuilder();
                            
                            outputBuilder.append("Broad Sweep Database: \n");
                            ArrayList<ProductInformation> products = info.getUpciteProducts();
                            ArrayList<ProductInformation> products2 = info.getWalmartProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder.append(
                                        products.get(i).getName().substring(0, Math.min(products.get(i).getName().length(), 20)) + "\n" +
                                        products.get(i).getPrice().substring(0, Math.min(products.get(i).getPrice().length(), 20)) + "  " +
                                        products.get(i).getUrl().substring(0, Math.min(products.get(i).getUrl().length(), 16)) + "\n\n"
                                );
                            }

                            outputBuilder2.append("Top Walmart Listings: \n");
                            //ArrayList<ProductInformation> products2 = info.getWalmartProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder2.append(
                                        products.get(i).getName().substring(0, Math.min(products.get(i).getName().length(), 20)) + "\n" +
                                        products.get(i).getPrice().substring(0, Math.min(products.get(i).getPrice().length(), 20)) + "  " +
                                        products.get(i).getUrl().substring(0, Math.min(products.get(i).getUrl().length(), 16)) + "\n\n"
                                );
                            }

                            outputBuilder3.append("Top Amazon Listings: \n");
                            products = info.getAmazonProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder3.append( "\n" +
                                        products.get(i).getName().substring(0, Math.min(products.get(i).getName().length(), 20)) + "\n" +
                                        products.get(i).getPrice().substring(0, Math.min(products.get(i).getPrice().length(), 20)) + "  " +
                                        products.get(i).getUrl().substring(0, Math.min(products.get(i).getUrl().length(), 16)) + "\n\n"
                                );
                            }

                            outputBuilder4.append("Top Best Buy Listings: \n");
                            products = info.getTargetProducts();
                            for (int i = 0; i < products.size(); i++) {
                                outputBuilder4.append( "\n" +
                                        products.get(i).getName().substring(0, Math.min(products.get(i).getName().length(), 20)) + "\n" +
                                        products.get(i).getPrice().substring(0, Math.min(products.get(i).getPrice().length(), 20)) + "  " +
                                        products.get(i).getUrl().substring(0, Math.min(products.get(i).getUrl().length(), 16)) + "\n\n"
                                );
                            }

                            textView.setText(".");
                            textView1.setText(outputBuilder.toString());
                            textView2.setText(outputBuilder2.toString());
                            textView3.setText(outputBuilder3.toString());
                            textView4.setText(outputBuilder4.toString());
                            
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