package com.example.pricecomparisonscanner.information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AllProductInformation {
    private ArrayList<ProductInformation> amazonProducts;
    private ArrayList<ProductInformation> walmartProducts;
    private ArrayList<ProductInformation> targetProducts;
    private ArrayList<ProductInformation> upciteProducts;

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
            //error - idk what to do lol
        }
        this.upciteProducts = products;
    }

    public AllProductInformation(ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts, ArrayList<ProductInformation> upciteProducts) {
        this.amazonProducts = amazonProducts;
        this.walmartProducts = walmartProducts;
        this.targetProducts = targetProducts;
        this.upciteProducts = upciteProducts;
    }

    public AllProductInformation(int n, ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts, ArrayList<ProductInformation> upciteProducts) {

        ArrayList<ProductInformation> nAmazon = (ArrayList<ProductInformation>) amazonProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nWalmart = (ArrayList<ProductInformation>) walmartProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nTarget = (ArrayList<ProductInformation>) targetProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nUpcite = (ArrayList<ProductInformation>) upciteProducts.stream().limit(n).collect(Collectors.toList());

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
        this.upciteProducts = nUpcite;
    }

    public AllProductInformation(int n, AllProductInformation productInformation) {
//        ArrayList<ProductInformation> nAmazon = (ArrayList<ProductInformation>) productInformation.getAmazonProducts().stream().limit(n).collect(Collectors.toList());
//        ArrayList<ProductInformation> nWalmart = (ArrayList<ProductInformation>) productInformation.getWalmartProducts().stream().limit(n).collect(Collectors.toList());
//        ArrayList<ProductInformation> nTarget = (ArrayList<ProductInformation>) productInformation.getTargetProducts().stream().limit(n).collect(Collectors.toList());
//        ArrayList<ProductInformation> nUpcite = (ArrayList<ProductInformation>) productInformation.getUpciteProducts().stream().limit(n).collect(Collectors.toList());

        ArrayList<ProductInformation> nAmazon = new ArrayList();
        ArrayList<ProductInformation> nWalmart = new ArrayList();
        ArrayList<ProductInformation> nTarget = new ArrayList();
        ArrayList<ProductInformation> nUpcite = new ArrayList();



        for (int i = 0; i < n && i < productInformation.getAmazonProducts().size(); i++) {
            nAmazon.add(productInformation.getAmazonProducts().get(i));
        }
        for (int i = 0; i < n && i < productInformation.getWalmartProducts().size(); i++) {
            nWalmart.add(productInformation.getWalmartProducts().get(i));
        }
        for (int i = 0; i < n && i < productInformation.getTargetProducts().size(); i++) {
            nTarget.add(productInformation.getTargetProducts().get(i));
        }
        for (int i = 0; i < n && i < productInformation.getUpciteProducts().size(); i++) {
            nUpcite.add(productInformation.getUpciteProducts().get(i));
        }

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
        this.upciteProducts = nUpcite;
    }

    @Override
    public String toString() {
        return "AllProductInformation{" +
                "amazonProducts=" + amazonProducts +
                ", walmartProducts=" + walmartProducts +
                ", targetProducts=" + targetProducts +
                ", upciteProducts=" + upciteProducts +
                "}\n";
    }
}
