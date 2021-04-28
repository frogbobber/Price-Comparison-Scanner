package com.example.pricecomparisonscanner.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.WebScraper;
import com.example.pricecomparisonscanner.analysis.DataProcessor;
import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.example.pricecomparisonscanner.ui.activities.ScannerActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class BarcodeActivity extends com.example.pricecomparisonscanner.ui.helpers.BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ScrollView scrollView;
//    private TextView textView;
//    private TextView textView1;
//    private TextView textView2;
//    private TextView textView3;
//    private TextView textView4;
    private AllProductInformation allProductInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.textView);
//        textView1 = findViewById(R.id.resultsTextView1);
//        textView2 = findViewById(R.id.resultsTextView2);
//        textView3 = findViewById(R.id.resultsTextView3);
//        textView4 = findViewById(R.id.resultsTextView4);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.Spinner);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        //initializing the adapter with our weeks array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weeks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); //giving adapter to the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = findViewById(R.id.textview6);
                if (allProductInformation == null) {
                    return;
                }
                if(position==0)
                {
                    try {
                        textView.setText(allProductInformation.getAmazonProducts().get(0).getName() + " " + allProductInformation.getAmazonProducts().get(0).getPrice());
                    } catch (Exception e) {
                        textView.setText("No Amazon Information, please try another source.");
                    }
                }
                else if(position==1)
                {
                    try {
                        textView.setText(allProductInformation.getWalmartProducts().get(0).getName() + " " + allProductInformation.getWalmartProducts().get(0).getPrice());
                    } catch (Exception e) {
                        textView.setText("No Walmart Information, please try another source.");
                    }
                }
                else if(position==2)
                {
                    try {
                        textView.setText(allProductInformation.getTargetProducts().get(0).getName() + " " + allProductInformation.getTargetProducts().get(0).getPrice());
                    } catch (Exception e) {
                        textView.setText("No Target Information, please try another source.");
                    }
                }
                else if(position==3)
                {
                    try {
                        textView.setText(allProductInformation.getTargetProducts().get(0).getName() + " " + allProductInformation.getTargetProducts().get(0).getPrice());
                    } catch (Exception e) {
                        textView.setText("No BestBuy Information, please try another source.");
                    }
                }
                else if(position==4)
                {
                    try {
                        textView.setText(allProductInformation.getUpciteProducts().get(0).getName() + " " + allProductInformation.getUpciteProducts().get(0).getPrice());
                    } catch (Exception e) {
                        textView.setText("No Broad Sweep Information, please try another source.");
                    }
                }
                if (textView.getText().equals(" ") || textView.getText().equals("")) {
                    textView.setText("Collecting Information, \nPlease try again in a few seconds.");
                }
                System.out.println(" position " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setEnabled(false);

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
//                textView.setText("");
//                textView1.setText("");
//                textView2.setText("");
//                textView3.setText("");
//                textView4.setText("");

                StringBuilder builder = new StringBuilder();

                builder.append("   Price Analytics: \n\n");
                builder.append("Best Value Product: \n" + dp.getBestListing().getName() + " " + dp.getBestListing().getPrice() + "\nat: " + dp.getBestListing().getUrl() + "\n\n");
                builder.append("Average Price: " + dp.getMean() + "\n");
                builder.append("Median Price: " + dp.getMedian() + "\n");
                builder.append("Variance: " + dp.getVariance() + "\n");

//                textView.setText(builder.toString());
            } else {
//                textView.setText("   Please scan a barcode first.");
//                textView1.setText("");
//                textView2.setText("");
//                textView3.setText("");
//                textView4.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


        Spinner spinner = findViewById(R.id.Spinner);
        spinner.setEnabled(true);

//        textView.setText("");
//        textView1.setText("");
//        textView2.setText("");
//        textView3.setText("");
//        textView4.setText("");
//        textView1.setMovementMethod(new ScrollingMovementMethod());
//        textView2.setMovementMethod(new ScrollingMovementMethod());
//        textView3.setMovementMethod(new ScrollingMovementMethod());
//        textView4.setMovementMethod(new ScrollingMovementMethod());

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
//                textView.setText("Cancelled");
            } else {
//                textView.setText(intentResult.getContents());

                Thread thread = new Thread(() -> {
                    try {
                        String inline = "";
                        String upc = intentResult.getContents();
                        String name = upc;
                        URL url = new URL("https://api.upcitemdb.com/prod/trial/lookup?upc=" + upc);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
//                        textView.setText("Connecting");
                        conn.connect();

                        int responseCode = conn.getResponseCode();
                        JSONObject jsonObject = null;

                        if (responseCode != 200) {
//                            textView.setText(responseCode + "");
                        } else {
                            Scanner scanner = new Scanner(url.openStream());
                            StringBuilder builder = new StringBuilder();
                            while (scanner.hasNext()) {
                                builder.append(scanner.nextLine());
                            }

                            inline = builder.toString();
                            scanner.close();

//                            textView.setText("Received Response: ");

                            jsonObject = new JSONObject(inline);
                            JSONArray jsonArray = jsonObject.getJSONArray("items").getJSONObject(0).getJSONArray("offers");
                            Double price1 = jsonArray.getJSONObject(0).getDouble("price");
                            name = jsonArray.getJSONObject(0).getString("title").replaceAll(" ", "+");
                        }

                        allProductInformation = WebScraper.getProductInformation(name, upc);
                        AllProductInformation info = allProductInformation;

                        if (jsonObject != null) {
                            info.setUpciteProducts(jsonObject);
                        }

                        info = new AllProductInformation(10, allProductInformation);

                        StringBuilder outputBuilder = new StringBuilder();
                        StringBuilder outputBuilder2 = new StringBuilder();
                        StringBuilder outputBuilder3 = new StringBuilder();
                        StringBuilder outputBuilder4 = new StringBuilder();

                        outputBuilder.append("Broad Sweep Database: \n");
                        ArrayList<ProductInformation> products = info.getUpciteProducts();
                        for (int i = 0; i < products.size(); i++) {
                            outputBuilder.append(
                                    products.get(i).getName().substring(0, Math.min(products.get(i).getName().length(), 20)) + "\n" +
                                            products.get(i).getPrice().substring(0, Math.min(products.get(i).getPrice().length(), 20)) + "  " +
                                            products.get(i).getUrl().substring(0, Math.min(products.get(i).getUrl().length(), 16)) + "\n\n"
                            );
                        }

                        outputBuilder2.append("Top Walmart Listings: \n");
                        products = info.getWalmartProducts();
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
//                        textView.setText(".");
//                        textView1.setText(outputBuilder.toString());
//                        textView2.setText(outputBuilder2.toString());
//                        textView3.setText(outputBuilder3.toString());
//                        textView4.setText(outputBuilder4.toString());
                        System.out.println("\n\nnew info - \n" + info + "\n - end info\n\n");

                        AllProductInformation finalInfo = info;
                        Thread mongoThread = new Thread(() -> {
                            com.example.pricecomparisonscanner.Database.ConnectionHelper.sendPrices(finalInfo);
                            System.out.println(com.example.pricecomparisonscanner.Database.ConnectionHelper.retrievePrices(upc));
                        });

                        mongoThread.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }
        }
    }

    @Override
    protected int getNavigationDrawerID() {
        return R.id.nav_barcode;
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

    public void onBackPressed() {
        Intent intent = new Intent(BarcodeActivity.this, ScannerActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
