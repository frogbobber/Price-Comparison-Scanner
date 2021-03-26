package com.example.pricecomparisonscanner.information;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AllProductInformation {
    private ArrayList<ProductInformation> amazonProducts;
    private ArrayList<ProductInformation> walmartProducts;
    private ArrayList<ProductInformation> targetProducts;

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

    public AllProductInformation(ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts) {
        this.amazonProducts = amazonProducts;
        this.walmartProducts = walmartProducts;
        this.targetProducts = targetProducts;
    }

    public AllProductInformation(int n, ArrayList<ProductInformation> amazonProducts, ArrayList<ProductInformation> walmartProducts, ArrayList<ProductInformation> targetProducts) {

        ArrayList<ProductInformation> nAmazon = (ArrayList<ProductInformation>) amazonProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nWalmart = (ArrayList<ProductInformation>) walmartProducts.stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nTarget = (ArrayList<ProductInformation>) targetProducts.stream().limit(n).collect(Collectors.toList());

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
    }

    public AllProductInformation(int n, AllProductInformation productInformation) {
        ArrayList<ProductInformation> nAmazon = (ArrayList<ProductInformation>) productInformation.getAmazonProducts().stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nWalmart = (ArrayList<ProductInformation>) productInformation.getWalmartProducts().stream().limit(n).collect(Collectors.toList());
        ArrayList<ProductInformation> nTarget = (ArrayList<ProductInformation>) productInformation.getTargetProducts().stream().limit(n).collect(Collectors.toList());

        this.amazonProducts = nAmazon;
        this.walmartProducts = nWalmart;
        this.targetProducts = nTarget;
    }

    @Override
    public String toString() {
        return "AllProductInformation{" +
                "amazonProducts=" + amazonProducts +
                ", walmartProducts=" + walmartProducts +
                ", targetProducts=" + targetProducts +
                "}\n";
    }
}
