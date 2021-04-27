package com.example.pricecomparisonscanner.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AllProductInformation {

    private long downloadTime;
    private ArrayList<ProductInformation> amazonProducts;
    private ArrayList<ProductInformation> walmartProducts;
    private ArrayList<ProductInformation> targetProducts;
    private ArrayList<ProductInformation> upciteProducts;
    private String upc;

    public long getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(long time) {this.downloadTime = time;}

    public ArrayList<ProductInformation> getAmazonProducts() {
        return amazonProducts;
    }
    public void setAmazonProducts(ArrayList<ProductInformation> amazonProducts) {
        this.amazonProducts = amazonProducts;
    }
    public ArrayList<ProductInformation> getWalmartProducts() {
        return walmartProducts;
    }
    public void setWalmartProducts(ArrayList<ProductInformation> walmartProducts) {
        this.walmartProducts = walmartProducts;
    }
    public ArrayList<ProductInformation> getTargetProducts() {
        return targetProducts;
    }
    public void setTargetProducts(ArrayList<ProductInformation> targetProducts) {
        this.targetProducts = targetProducts;
    }
    public ArrayList<ProductInformation> getUpciteProducts() {
        return upciteProducts;
    }
    public void setUpciteProducts(ArrayList<ProductInformation> upciteProducts) {
        this.upciteProducts = upciteProducts;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpciteProducts(JSONObject jsonObject) {
        ArrayList<ProductInformation> products = new ArrayList();
        try {
            JSONArray items = jsonObject.getJSONArray("items").getJSONObject(0).getJSONArray("offers");
            for (int i = 0; i < items.length(); i++) {
                products.add(new ProductInformation(
                        items.getJSONObject(i).getString("link"),
                        items.getJSONObject(i).getString("title"),
                        items.getJSONObject(i).getDouble("price") + ""
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.upciteProducts = products;
    }

    public AllProductInformation(ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts, ArrayList<ProductInformation> upciteProducts, String upc) {
        this.downloadTime = System.currentTimeMillis();
        this.amazonProducts = amazonProducts;
        this.walmartProducts = walmartProducts;
        this.targetProducts = targetProducts;
        this.upciteProducts = upciteProducts;
        this.upc = upc;
    }

    public AllProductInformation(int n, ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts, ArrayList<ProductInformation> upciteProducts) {

        this.downloadTime = System.currentTimeMillis();

        ArrayList<ProductInformation> nAmazon = (ArrayList<ProductInformation>) amazonProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nWalmart = (ArrayList<ProductInformation>) walmartProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nTarget = (ArrayList<ProductInformation>) targetProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nUpcite = (ArrayList<ProductInformation>) upciteProducts.stream().limit(n).collect(Collectors.toList());

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
        this.upciteProducts = nUpcite;

        this.upc = upc;
    }

    public AllProductInformation(int n, AllProductInformation productInformation) {

        this.downloadTime = System.currentTimeMillis();

        ArrayList<ProductInformation> nAmazon = new ArrayList();
        ArrayList<ProductInformation> nWalmart = new ArrayList();
        ArrayList<ProductInformation> nTarget = new ArrayList();
        ArrayList<ProductInformation> nUpcite = new ArrayList();

        if (productInformation.getAmazonProducts() != null) {
            for (int i = 0; i < n && i < productInformation.getAmazonProducts().size(); i++) {
                nAmazon.add(productInformation.getAmazonProducts().get(i));
            }
        }
        if (productInformation.getWalmartProducts() != null) {
            for (int i = 0; i < n && i < productInformation.getWalmartProducts().size(); i++) {
                nWalmart.add(productInformation.getWalmartProducts().get(i));
            }
        }
        if (productInformation.getTargetProducts() != null) {
            for (int i = 0; i < n && i < productInformation.getTargetProducts().size(); i++) {
                nTarget.add(productInformation.getTargetProducts().get(i));
            }
        }
        if (productInformation.getUpciteProducts() != null) {
            for (int i = 0; i < n && i < productInformation.getUpciteProducts().size(); i++) {
                nUpcite.add(productInformation.getUpciteProducts().get(i));
            }
        }

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
        this.upciteProducts = nUpcite;

        this.upc = productInformation.getUpc();
    }

    @Override
    public String toString() {
        return "AllProductInformation{" +
                "amazonProducts=" + amazonProducts +
                ", walmartProducts=" + walmartProducts +
                ", targetProducts=" + targetProducts +
                ", upciteProducts=" + upciteProducts +
                ", downloadTime=" + this.downloadTime +
                ", upc=" + this.upc +
                "}\n";
    }
}
