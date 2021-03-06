package com.example.pricecomparisonscanner.information;

public class ProductInformation {
    String url;
    String name;
    String price;
    // possibly an image ??

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public ProductInformation(String url, String name, String price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductInformation{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                "}\n";
    }
}
