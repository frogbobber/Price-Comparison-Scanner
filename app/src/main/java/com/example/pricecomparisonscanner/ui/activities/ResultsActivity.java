package com.example.pricecomparisonscanner.ui.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pricecomparisonscanner.R;
import com.example.pricecomparisonscanner.analysis.DataProcessor;
import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.example.pricecomparisonscanner.ui.helpers.ExpandableListAdapter;
import com.example.pricecomparisonscanner.ui.helpers.HelpDataDump;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

public class ResultsActivity extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<String> arr;
    TextView title;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        lineChart = (LineChart) findViewById(R.id.lineChart);
        title = (TextView) findViewById(R.id.resultsTitle);
        setTitle("Price History");
        String jsonString = "{\"_id\" : ObjectId(\"60845e960c0998fb88d9614a\"),\"amazonProducts\" : [ {\"name\" : \"Jell-o Sugar-free Instant Pudding &amp; Pie Filling, White Chocolate,1 Ounce (Pack of 4)\",\"price\" : \"$8.31\",\"url\" : \"https://www.amazon.com/Jell-Sugar-free-Instant-Pudding-Chocolate/dp/B00CK8T1HS/ref=sr_1_1?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-1\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 6)\",\"price\" : \"$12.39\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00JD788AK/ref=sr_1_2?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-2\" }, {\"name\" : \"JELLO Instant Chocolate Pudding Mix (3.9oz Boxes, Pack of 6)\",\"price\" : \"$13.34\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Ounce/dp/B00K0PRD2S/ref=sr_1_3?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-3\" }, {\"name\" : \"\",\"price\" : \"$23.52\",\"url\" : \"https://aax-us-east.amazon-adsystem.com/x/c/Qua1s7wm-lfTZt-hfuzIuhMAAAF5BRF0pAEAAAH2AWp3I4o/https://www.amazon.com/dp/B000E1HVF2?pd_rd_i=B000E1HVF2&pd_rd_w=0Jpez&pf_rd_p=3465d0d7-4e28-4692-b633-326c458deaa4&pd_rd_wg=nrzBv&pf_rd_r=N048FD7MERGPD6QCQD06&pd_rd_r=d5acbddb-46d1-46f4-bf0a-0d057b7c74af\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (5.9 oz Boxes, Pack of 24)\", \"price\" : \"$30.96\", \"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B00HQKRSWU/ref=sr_1_4?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-4\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Sugar-Free Fat Free Pudding &amp; Pie Filling (1.4 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/Jell-Sugar-Free-Instant-Chocolate-1-4-Ounce/dp/B000E1BLMG/ref=sr_1_5?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-5\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B000E1DSFE/ref=sr_1_6?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-6\" }, {\"name\" : \"Jell-O Instant White Chocolate Pudding &amp; Pie Filling (3.3 oz Boxes, Pack of 6)\",\"price\" : \"$13.68\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00GJN7H4O/ref=sr_1_7?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-7\" }, {\"name\" : \"JELL-O Jello Instant Pudding and Pie Filling 4 Boxes (Chocolate Fudge)\",\"price\" : \"$10.99\",\"url\" : \"https://www.amazon.com/JELL-Instant-Pudding-Filling-Chocolate/dp/B00AB40CIU/ref=sr_1_8?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-8\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding &amp; Pie Filling (4-Pack)\",\"price\" : \"$9.63\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Filling/dp/B00GQCC0WM/ref=sr_1_9?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-9\" }],\"downloadTime\" : \"1619287702621\",\"targetProducts\" : [ ],\"upc\" : \"043000204313\",\"upciteProducts\" : [ {\"name\" : \"Jell-O Chocolate Instant Pudding & Pie Filling - 3.9 Ounces\",\"price\" : \"1.29\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=x2x253t213y2e484v2&tid=1&seq=1619287699&plt=a4c21fc2d983f6dde0723d62f5563e9d\" }, {\"name\" : \"Jello 4113 Pudding & Pie Filling - Instant Chocolate Case Of 24\",\"price\" : \"37.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2t253z2v2y2f4c4z2&tid=1&seq=1619287699&plt=19a954d15ab93055df834fee14560b1b\" }, {\"name\" : \"Jell-O Instant Pudding and Pie Filling - Chocolate - 1 Box (3.9 oz)\",\"price\" : \"1.03\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2z213x2z2e4c4&tid=1&seq=1619287699&plt=928de5b77e82786b88ffc5f585b49d68\" }, {\"name\" : \"Instant Chocolate Pudding & Pie Filling 3.9 OZ BOX\",\"price\" : \"1.19\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2r2632303x26454q2&tid=1&seq=1619287699&plt=7e2c55c07c795c58903b8e1721bdd7ea\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding Mix, 3.9 oz Box\",\"price\" : \"0.92\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=y2r26313030394b4&tid=1&seq=1619287699&plt=90b8542043684cd0133d2fde11c93372\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding & Pie Filling - 3.9oz\",\"price\" : \"0.99\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=23w2x2v2x213c4b4&tid=1&seq=1619287699&plt=cc3558b3edf66b1b784498c516efbf7d\" }, {\"name\" : \"Jell-O - Pudding & Pie Filling - Instant Chocolate 3.90 oz\",\"price\" : \"0.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2330303037474y2&tid=1&seq=1619287699&plt=ef3414724de3a37e7e089df0bd364a71\" }],\"walmartProducts\" : [ ] }";
        String jsonString2 = "{\"_id\" : ObjectId(\"60845e960c0998fb88d9614a\"),\"amazonProducts\" : [ {\"name\" : \"Jell-o Sugar-free Instant Pudding &amp; Pie Filling, White Chocolate,1 Ounce (Pack of 4)\",\"price\" : \"$8.31\",\"url\" : \"https://www.amazon.com/Jell-Sugar-free-Instant-Pudding-Chocolate/dp/B00CK8T1HS/ref=sr_1_1?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-1\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 6)\",\"price\" : \"$12.39\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00JD788AK/ref=sr_1_2?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-2\" }, {\"name\" : \"JELLO Instant Chocolate Pudding Mix (3.9oz Boxes, Pack of 6)\",\"price\" : \"$13.34\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Ounce/dp/B00K0PRD2S/ref=sr_1_3?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-3\" }, {\"name\" : \"\",\"price\" : \"$23.52\",\"url\" : \"https://aax-us-east.amazon-adsystem.com/x/c/Qua1s7wm-lfTZt-hfuzIuhMAAAF5BRF0pAEAAAH2AWp3I4o/https://www.amazon.com/dp/B000E1HVF2?pd_rd_i=B000E1HVF2&pd_rd_w=0Jpez&pf_rd_p=3465d0d7-4e28-4692-b633-326c458deaa4&pd_rd_wg=nrzBv&pf_rd_r=N048FD7MERGPD6QCQD06&pd_rd_r=d5acbddb-46d1-46f4-bf0a-0d057b7c74af\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (5.9 oz Boxes, Pack of 24)\", \"price\" : \"$30.96\", \"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B00HQKRSWU/ref=sr_1_4?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-4\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Sugar-Free Fat Free Pudding &amp; Pie Filling (1.4 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/Jell-Sugar-Free-Instant-Chocolate-1-4-Ounce/dp/B000E1BLMG/ref=sr_1_5?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-5\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B000E1DSFE/ref=sr_1_6?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-6\" }, {\"name\" : \"Jell-O Instant White Chocolate Pudding &amp; Pie Filling (3.3 oz Boxes, Pack of 6)\",\"price\" : \"$13.68\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00GJN7H4O/ref=sr_1_7?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-7\" }, {\"name\" : \"JELL-O Jello Instant Pudding and Pie Filling 4 Boxes (Chocolate Fudge)\",\"price\" : \"$10.99\",\"url\" : \"https://www.amazon.com/JELL-Instant-Pudding-Filling-Chocolate/dp/B00AB40CIU/ref=sr_1_8?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-8\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding &amp; Pie Filling (4-Pack)\",\"price\" : \"$9.63\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Filling/dp/B00GQCC0WM/ref=sr_1_9?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-9\" }],\"downloadTime\" : \"1619645844000\",\"targetProducts\" : [ ],\"upc\" : \"043000204313\",\"upciteProducts\" : [ {\"name\" : \"Jell-O Chocolate Instant Pudding & Pie Filling - 3.9 Ounces\",\"price\" : \"1.29\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=x2x253t213y2e484v2&tid=1&seq=1619287699&plt=a4c21fc2d983f6dde0723d62f5563e9d\" }, {\"name\" : \"Jello 4113 Pudding & Pie Filling - Instant Chocolate Case Of 24\",\"price\" : \"37.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2t253z2v2y2f4c4z2&tid=1&seq=1619287699&plt=19a954d15ab93055df834fee14560b1b\" }, {\"name\" : \"Jell-O Instant Pudding and Pie Filling - Chocolate - 1 Box (3.9 oz)\",\"price\" : \"1.03\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2z213x2z2e4c4&tid=1&seq=1619287699&plt=928de5b77e82786b88ffc5f585b49d68\" }, {\"name\" : \"Instant Chocolate Pudding & Pie Filling 3.9 OZ BOX\",\"price\" : \"1.19\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2r2632303x26454q2&tid=1&seq=1619287699&plt=7e2c55c07c795c58903b8e1721bdd7ea\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding Mix, 3.9 oz Box\",\"price\" : \"0.92\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=y2r26313030394b4&tid=1&seq=1619287699&plt=90b8542043684cd0133d2fde11c93372\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding & Pie Filling - 3.9oz\",\"price\" : \"1.44\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=23w2x2v2x213c4b4&tid=1&seq=1619287699&plt=cc3558b3edf66b1b784498c516efbf7d\" }, {\"name\" : \"Jell-O - Pudding & Pie Filling - Instant Chocolate 3.90 oz\",\"price\" : \"0.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2330303037474y2&tid=1&seq=1619287699&plt=ef3414724de3a37e7e089df0bd364a71\" }],\"walmartProducts\" : [ ] }";
        String jsonString3 = "{\"_id\" : ObjectId(\"60845e960c0998fb88d9614a\"),\"amazonProducts\" : [ {\"name\" : \"Jell-o Sugar-free Instant Pudding &amp; Pie Filling, White Chocolate,1 Ounce (Pack of 4)\",\"price\" : \"$8.31\",\"url\" : \"https://www.amazon.com/Jell-Sugar-free-Instant-Pudding-Chocolate/dp/B00CK8T1HS/ref=sr_1_1?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-1\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 6)\",\"price\" : \"$12.39\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00JD788AK/ref=sr_1_2?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-2\" }, {\"name\" : \"JELLO Instant Chocolate Pudding Mix (3.9oz Boxes, Pack of 6)\",\"price\" : \"$13.34\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Ounce/dp/B00K0PRD2S/ref=sr_1_3?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-3\" }, {\"name\" : \"\",\"price\" : \"$23.52\",\"url\" : \"https://aax-us-east.amazon-adsystem.com/x/c/Qua1s7wm-lfTZt-hfuzIuhMAAAF5BRF0pAEAAAH2AWp3I4o/https://www.amazon.com/dp/B000E1HVF2?pd_rd_i=B000E1HVF2&pd_rd_w=0Jpez&pf_rd_p=3465d0d7-4e28-4692-b633-326c458deaa4&pd_rd_wg=nrzBv&pf_rd_r=N048FD7MERGPD6QCQD06&pd_rd_r=d5acbddb-46d1-46f4-bf0a-0d057b7c74af\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (5.9 oz Boxes, Pack of 24)\", \"price\" : \"$30.96\", \"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B00HQKRSWU/ref=sr_1_4?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-4\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Sugar-Free Fat Free Pudding &amp; Pie Filling (1.4 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/Jell-Sugar-Free-Instant-Chocolate-1-4-Ounce/dp/B000E1BLMG/ref=sr_1_5?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-5\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B000E1DSFE/ref=sr_1_6?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-6\" }, {\"name\" : \"Jell-O Instant White Chocolate Pudding &amp; Pie Filling (3.3 oz Boxes, Pack of 6)\",\"price\" : \"$13.68\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00GJN7H4O/ref=sr_1_7?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-7\" }, {\"name\" : \"JELL-O Jello Instant Pudding and Pie Filling 4 Boxes (Chocolate Fudge)\",\"price\" : \"$10.99\",\"url\" : \"https://www.amazon.com/JELL-Instant-Pudding-Filling-Chocolate/dp/B00AB40CIU/ref=sr_1_8?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-8\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding &amp; Pie Filling (4-Pack)\",\"price\" : \"$9.63\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Filling/dp/B00GQCC0WM/ref=sr_1_9?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-9\" }],\"downloadTime\" : \"1619732244000\",\"targetProducts\" : [ ],\"upc\" : \"043000204313\",\"upciteProducts\" : [ {\"name\" : \"Jell-O Chocolate Instant Pudding & Pie Filling - 3.9 Ounces\",\"price\" : \"1.29\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=x2x253t213y2e484v2&tid=1&seq=1619287699&plt=a4c21fc2d983f6dde0723d62f5563e9d\" }, {\"name\" : \"Jello 4113 Pudding & Pie Filling - Instant Chocolate Case Of 24\",\"price\" : \"37.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2t253z2v2y2f4c4z2&tid=1&seq=1619287699&plt=19a954d15ab93055df834fee14560b1b\" }, {\"name\" : \"Jell-O Instant Pudding and Pie Filling - Chocolate - 1 Box (3.9 oz)\",\"price\" : \"1.03\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2z213x2z2e4c4&tid=1&seq=1619287699&plt=928de5b77e82786b88ffc5f585b49d68\" }, {\"name\" : \"Instant Chocolate Pudding & Pie Filling 3.9 OZ BOX\",\"price\" : \"1.19\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2r2632303x26454q2&tid=1&seq=1619287699&plt=7e2c55c07c795c58903b8e1721bdd7ea\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding Mix, 3.9 oz Box\",\"price\" : \"0.92\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=y2r26313030394b4&tid=1&seq=1619287699&plt=90b8542043684cd0133d2fde11c93372\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding & Pie Filling - 3.9oz\",\"price\" : \"0.77\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=23w2x2v2x213c4b4&tid=1&seq=1619287699&plt=cc3558b3edf66b1b784498c516efbf7d\" }, {\"name\" : \"Jell-O - Pudding & Pie Filling - Instant Chocolate 3.90 oz\",\"price\" : \"0.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2330303037474y2&tid=1&seq=1619287699&plt=ef3414724de3a37e7e089df0bd364a71\" }],\"walmartProducts\" : [ ] }";
        String jsonString4 = "{\"_id\" : ObjectId(\"60845e960c0998fb88d9614a\"),\"amazonProducts\" : [ {\"name\" : \"Jell-o Sugar-free Instant Pudding &amp; Pie Filling, White Chocolate,1 Ounce (Pack of 4)\",\"price\" : \"$8.31\",\"url\" : \"https://www.amazon.com/Jell-Sugar-free-Instant-Pudding-Chocolate/dp/B00CK8T1HS/ref=sr_1_1?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-1\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 6)\",\"price\" : \"$12.39\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00JD788AK/ref=sr_1_2?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-2\" }, {\"name\" : \"JELLO Instant Chocolate Pudding Mix (3.9oz Boxes, Pack of 6)\",\"price\" : \"$13.34\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Ounce/dp/B00K0PRD2S/ref=sr_1_3?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-3\" }, {\"name\" : \"\",\"price\" : \"$23.52\",\"url\" : \"https://aax-us-east.amazon-adsystem.com/x/c/Qua1s7wm-lfTZt-hfuzIuhMAAAF5BRF0pAEAAAH2AWp3I4o/https://www.amazon.com/dp/B000E1HVF2?pd_rd_i=B000E1HVF2&pd_rd_w=0Jpez&pf_rd_p=3465d0d7-4e28-4692-b633-326c458deaa4&pd_rd_wg=nrzBv&pf_rd_r=N048FD7MERGPD6QCQD06&pd_rd_r=d5acbddb-46d1-46f4-bf0a-0d057b7c74af\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (5.9 oz Boxes, Pack of 24)\", \"price\" : \"$30.96\", \"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B00HQKRSWU/ref=sr_1_4?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-4\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Sugar-Free Fat Free Pudding &amp; Pie Filling (1.4 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/Jell-Sugar-Free-Instant-Chocolate-1-4-Ounce/dp/B000E1BLMG/ref=sr_1_5?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-5\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B000E1DSFE/ref=sr_1_6?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-6\" }, {\"name\" : \"Jell-O Instant White Chocolate Pudding &amp; Pie Filling (3.3 oz Boxes, Pack of 6)\",\"price\" : \"$13.68\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00GJN7H4O/ref=sr_1_7?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-7\" }, {\"name\" : \"JELL-O Jello Instant Pudding and Pie Filling 4 Boxes (Chocolate Fudge)\",\"price\" : \"$10.99\",\"url\" : \"https://www.amazon.com/JELL-Instant-Pudding-Filling-Chocolate/dp/B00AB40CIU/ref=sr_1_8?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-8\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding &amp; Pie Filling (4-Pack)\",\"price\" : \"$9.63\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Filling/dp/B00GQCC0WM/ref=sr_1_9?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-9\" }],\"downloadTime\" : \"1619818644000\",\"targetProducts\" : [ ],\"upc\" : \"043000204313\",\"upciteProducts\" : [ {\"name\" : \"Jell-O Chocolate Instant Pudding & Pie Filling - 3.9 Ounces\",\"price\" : \"1.29\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=x2x253t213y2e484v2&tid=1&seq=1619287699&plt=a4c21fc2d983f6dde0723d62f5563e9d\" }, {\"name\" : \"Jello 4113 Pudding & Pie Filling - Instant Chocolate Case Of 24\",\"price\" : \"37.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2t253z2v2y2f4c4z2&tid=1&seq=1619287699&plt=19a954d15ab93055df834fee14560b1b\" }, {\"name\" : \"Jell-O Instant Pudding and Pie Filling - Chocolate - 1 Box (3.9 oz)\",\"price\" : \"1.03\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2z213x2z2e4c4&tid=1&seq=1619287699&plt=928de5b77e82786b88ffc5f585b49d68\" }, {\"name\" : \"Instant Chocolate Pudding & Pie Filling 3.9 OZ BOX\",\"price\" : \"1.19\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2r2632303x26454q2&tid=1&seq=1619287699&plt=7e2c55c07c795c58903b8e1721bdd7ea\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding Mix, 3.9 oz Box\",\"price\" : \"0.92\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=y2r26313030394b4&tid=1&seq=1619287699&plt=90b8542043684cd0133d2fde11c93372\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding & Pie Filling - 3.9oz\",\"price\" : \"0.88\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=23w2x2v2x213c4b4&tid=1&seq=1619287699&plt=cc3558b3edf66b1b784498c516efbf7d\" }, {\"name\" : \"Jell-O - Pudding & Pie Filling - Instant Chocolate 3.90 oz\",\"price\" : \"0.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2330303037474y2&tid=1&seq=1619287699&plt=ef3414724de3a37e7e089df0bd364a71\" }],\"walmartProducts\" : [ ] }";
        String jsonString5 = "{\"_id\" : ObjectId(\"60845e960c0998fb88d9614a\"),\"amazonProducts\" : [ {\"name\" : \"Jell-o Sugar-free Instant Pudding &amp; Pie Filling, White Chocolate,1 Ounce (Pack of 4)\",\"price\" : \"$8.31\",\"url\" : \"https://www.amazon.com/Jell-Sugar-free-Instant-Pudding-Chocolate/dp/B00CK8T1HS/ref=sr_1_1?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-1\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 6)\",\"price\" : \"$12.39\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00JD788AK/ref=sr_1_2?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-2\" }, {\"name\" : \"JELLO Instant Chocolate Pudding Mix (3.9oz Boxes, Pack of 6)\",\"price\" : \"$13.34\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Ounce/dp/B00K0PRD2S/ref=sr_1_3?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-3\" }, {\"name\" : \"\",\"price\" : \"$23.52\",\"url\" : \"https://aax-us-east.amazon-adsystem.com/x/c/Qua1s7wm-lfTZt-hfuzIuhMAAAF5BRF0pAEAAAH2AWp3I4o/https://www.amazon.com/dp/B000E1HVF2?pd_rd_i=B000E1HVF2&pd_rd_w=0Jpez&pf_rd_p=3465d0d7-4e28-4692-b633-326c458deaa4&pd_rd_wg=nrzBv&pf_rd_r=N048FD7MERGPD6QCQD06&pd_rd_r=d5acbddb-46d1-46f4-bf0a-0d057b7c74af\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Pudding &amp; Pie Filling (5.9 oz Boxes, Pack of 24)\", \"price\" : \"$30.96\", \"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B00HQKRSWU/ref=sr_1_4?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-4\" }, {\"name\" : \"Jell-O Instant Chocolate Fudge Sugar-Free Fat Free Pudding &amp; Pie Filling (1.4 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/Jell-Sugar-Free-Instant-Chocolate-1-4-Ounce/dp/B000E1BLMG/ref=sr_1_5?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-5\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding &amp; Pie Filling (3.9 oz Boxes, Pack of 24)\",\"price\" : \"$23.76\",\"url\" : \"https://www.amazon.com/JELL-Instant-Chocolate-Pudding-Filling/dp/B000E1DSFE/ref=sr_1_6?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-6\" }, {\"name\" : \"Jell-O Instant White Chocolate Pudding &amp; Pie Filling (3.3 oz Boxes, Pack of 6)\",\"price\" : \"$13.68\",\"url\" : \"https://www.amazon.com/JELL-Chocolate-Instant-Pudding-Filling/dp/B00GJN7H4O/ref=sr_1_7?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-7\" }, {\"name\" : \"JELL-O Jello Instant Pudding and Pie Filling 4 Boxes (Chocolate Fudge)\",\"price\" : \"$10.99\",\"url\" : \"https://www.amazon.com/JELL-Instant-Pudding-Filling-Chocolate/dp/B00AB40CIU/ref=sr_1_8?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-8\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding &amp; Pie Filling (4-Pack)\",\"price\" : \"$9.63\",\"url\" : \"https://www.amazon.com/Jell-Chocolate-Instant-Pudding-Filling/dp/B00GQCC0WM/ref=sr_1_9?dchild=1&keywords=Jell-O+Chocolate+Instant+Pudding&qid=1619287700&sr=8-9\" }],\"downloadTime\" : \"1619905044000\",\"targetProducts\" : [ ],\"upc\" : \"043000204313\",\"upciteProducts\" : [ {\"name\" : \"Jell-O Chocolate Instant Pudding & Pie Filling - 3.9 Ounces\",\"price\" : \"1.29\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=x2x253t213y2e484v2&tid=1&seq=1619287699&plt=a4c21fc2d983f6dde0723d62f5563e9d\" }, {\"name\" : \"Jello 4113 Pudding & Pie Filling - Instant Chocolate Case Of 24\",\"price\" : \"37.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2t253z2v2y2f4c4z2&tid=1&seq=1619287699&plt=19a954d15ab93055df834fee14560b1b\" }, {\"name\" : \"Jell-O Instant Pudding and Pie Filling - Chocolate - 1 Box (3.9 oz)\",\"price\" : \"1.03\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2z213x2z2e4c4&tid=1&seq=1619287699&plt=928de5b77e82786b88ffc5f585b49d68\" }, {\"name\" : \"Instant Chocolate Pudding & Pie Filling 3.9 OZ BOX\",\"price\" : \"1.19\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=u2r2632303x26454q2&tid=1&seq=1619287699&plt=7e2c55c07c795c58903b8e1721bdd7ea\" }, {\"name\" : \"Jell-O Chocolate Instant Pudding Mix, 3.9 oz Box\",\"price\" : \"0.92\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=y2r26313030394b4&tid=1&seq=1619287699&plt=90b8542043684cd0133d2fde11c93372\" }, {\"name\" : \"Jell-O Instant Chocolate Pudding & Pie Filling - 3.9oz\",\"price\" : \"0.90\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=23w2x2v2x213c4b4&tid=1&seq=1619287699&plt=cc3558b3edf66b1b784498c516efbf7d\" }, {\"name\" : \"Jell-O - Pudding & Pie Filling - Instant Chocolate 3.90 oz\",\"price\" : \"0.0\",\"url\" : \"https://www.upcitemdb.com/norob/alink/?id=v2q2330303037474y2&tid=1&seq=1619287699&plt=ef3414724de3a37e7e089df0bd364a71\" }],\"walmartProducts\" : [ ] }";

        ArrayList<String> arr = new ArrayList<String>();
        arr.add(jsonString);
        arr.add(jsonString2);
        arr.add(jsonString3);
        arr.add(jsonString4);
        arr.add(jsonString5);
        setChartData(arr);
    }

    public void setChartData(ArrayList<String> arr){
        try {
            ArrayList<AllProductInformation> api = JSONtoAllProductInformation(arr);
            setData(api);
        } catch (JSONException | CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    private ArrayList<AllProductInformation> JSONtoAllProductInformation(ArrayList<String> data) throws JSONException {
        ArrayList<AllProductInformation> allProductInformation = new ArrayList<AllProductInformation>();
        ArrayList<ProductInformation> amazonArr = new ArrayList<ProductInformation>();
        ArrayList<ProductInformation> targetArr = new ArrayList<ProductInformation>();
        ArrayList<ProductInformation> walmartArr = new ArrayList<ProductInformation>();
        ArrayList<ProductInformation> upciteArr = new ArrayList<ProductInformation>();

        for(int i = 0; i < data.size(); i++){
            JSONObject obj = new JSONObject(data.get(i));
            if(i == 0){
                String t = obj.getString("_id");
                t = t.replace("ObjectId(\"", "");
                t = t.replace("\")", "");
                title.setText(t + " Price History");
            }
            JSONArray amazonProducts = obj.getJSONArray("amazonProducts");
            JSONArray targetProducts = obj.getJSONArray("targetProducts");
            JSONArray walmartProducts = obj.getJSONArray("walmartProducts");
            JSONArray upciteProducts = obj.getJSONArray("upciteProducts");
            for (int j = 0; j < amazonProducts.length(); j++) {
                amazonArr.add(new ProductInformation(amazonProducts.getJSONObject(j).getString("url"),
                        amazonProducts.getJSONObject(j).getString("name"), amazonProducts.getJSONObject(j).getString("price")));
            }

            for (int j = 0; j < targetProducts.length(); j++) {
                targetArr.add(new ProductInformation(amazonProducts.getJSONObject(j).getString("url"),
                        amazonProducts.getJSONObject(j).getString("name"), amazonProducts.getJSONObject(j).getString("price")));
            }

            for (int j = 0; j < walmartProducts.length(); j++) {
                walmartArr.add(new ProductInformation(amazonProducts.getJSONObject(j).getString("url"),
                        amazonProducts.getJSONObject(j).getString("name"), amazonProducts.getJSONObject(j).getString("price")));
            }

            for (int j = 0; j < upciteProducts.length(); j++) {
                upciteArr.add(new ProductInformation(amazonProducts.getJSONObject(j).getString("url"),
                        amazonProducts.getJSONObject(j).getString("name"), amazonProducts.getJSONObject(j).getString("price")));
            }
            Long time = obj.getLong("downloadTime");

            allProductInformation.add(new AllProductInformation(amazonArr, targetArr, walmartArr, upciteArr, "ignore"));
            allProductInformation.get(i).setDownloadTime(time);
        }
        return allProductInformation;
    }

    private void setData(ArrayList<AllProductInformation> data) throws JSONException, CloneNotSupportedException {
        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXES = new ArrayList<>();
        int i = 0;
        for(AllProductInformation instance: data){
            DataProcessor dp = new DataProcessor(instance, false, false, false);
            Date date = new Date(instance.getDownloadTime());
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);
            //System.out.println(formatted);
            format.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
            formatted = format.format(date);
            //System.out.println(formatted);
            String price = dp.getBestListing().getPrice();
            price = price.replace("$", "");
            System.out.println("yAXES: " + price + " " + Integer.toString(i));
            System.out.println("yAXES: " + Integer.toString(i) + " " + formatted);
            yAXES.add(new Entry(i, Float.parseFloat(price)));
            xAXES.add(i, formatted);
            i++;
        }

        String[] xaxes = new String[xAXES.size()];

        for(i = 0; i < xAXES.size(); i++){
            xaxes[i] = xAXES.get(i);
        }

        ArrayList<ILineDataSet> lineDataSet = new ArrayList<>();
        LineDataSet line = new LineDataSet(yAXES, "History");
        line.setDrawCircles(false);
        line.setColor(Color.BLUE);

        lineDataSet.add(line);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(65f);
    }

    protected int getNavigationDrawerID() {
        return R.id.nav_results;
    }
}
