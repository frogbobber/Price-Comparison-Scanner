package com.example.pricecomparisonscanner.sorter;

import com.example.pricecomparisonscanner.information.ProductInformation;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TargetExtractor extends ProductExtractor{
    public ArrayList<ProductInformation> extractProducts(Document document, String keyWord) { // actually for bestbuy
        ArrayList<ProductInformation> products = new ArrayList<>();
        int i;
        int ei;
        URL redSkyURL = null;
        String apiKey;
        Elements scripts = document.select("script");
        for(Element script : scripts) {
            String data = script.data();
            i = data.indexOf("\"storiesV1\":{\"apiKey\":");
            ei = data.indexOf("\"}},\"graphql\"");
            if(i != -1) {
                apiKey = data.substring(i+23, ei);
                //System.out.println(data);
                //System.out.println(apiKey);
                try {
                    redSkyURL = new URL("https://redsky.target.com/redsky_aggregations/v1/web/plp_search_v1?key=" + apiKey
                            + "&channel=WEB&count=24&default_purchasability_filter=true&include_sponsored=true&keyword="
                            + keyWord + "&offset=0&page=%2Fs%2Ftoothbrush&platform=desktop&pricing_store_id=2427&scheduled_delivery_store_id=1935&store_ids=2427%2C1941%2C1110%2C1935%2C2065&useragent=Mozilla%2F5.0+%28Windows+NT+10.0%3B+Win64%3B+x64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F89.0.4389.114+Safari%2F537.36&visitor_id=01778DB72F770201821CD9EAB98CCD03");
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        //System.out.println(redSkyURL);
        try {
            HttpURLConnection sky = (HttpURLConnection) redSkyURL.openConnection();
            sky.setRequestMethod("GET");
            sky.connect();

            Scanner scanner = new Scanner(redSkyURL.openStream());
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }

            String inline = builder.toString();
            scanner.close();

            JSONObject jsonObject = new JSONObject(inline);

            //setup
            JSONArray data = (JSONArray) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("search")).get("products");

            int itrData = data.length();

            for (i = 0; i < itrData; i++) {
                JSONObject next = (JSONObject) data.get(i);
                JSONObject item = (JSONObject) next.get("item");
                JSONObject price = (JSONObject) next.get("price");

                //get item name
                String itemName = (String) ((JSONObject) item.get("product_description")).get("title");
                //System.out.println(itemName);


                //get Price
                String itemPrice = price.get("current_retail").toString();
                //System.out.println(itemPrice);

                //get URL
                String itemURL = (String) ((JSONObject) item.get("enrichment")).get("buy_url");
                //System.out.println(itemURL);

                products.add(new ProductInformation(itemURL, itemName, itemPrice));
            }
            System.out.println("Sorted Target");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ArrayList<ProductInformation> extractProducts(Document document) {
        return null;
    }
}
