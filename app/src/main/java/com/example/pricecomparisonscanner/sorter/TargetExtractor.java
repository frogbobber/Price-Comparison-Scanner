package com.example.pricecomparisonscanner.sorter;

import com.example.pricecomparisonscanner.information.ProductInformation;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class TargetExtractor extends ProductExtractor{
    @Override
    public ArrayList<ProductInformation> extractProducts(Document document) { // actually for bestbuy
        ArrayList<ProductInformation> products = new ArrayList<>();

        Elements elements = document.select("li[class*=sku-item]");//class="sg-col-inner"

        System.out.println(elements.size());

        elements.forEach(e -> {
            products.add(new ProductInformation(
                    e.select("h4[class*=sku-header]").select("a").attr("abs:href"), // get the url
                    e.select("h4[class*=sku-header]").select("a").html(), // name
                    e.select("div[class*=priceView-hero-price priceView-customer-price]").select("span").first().html() // price
            ));
        });
        //System.out.println(document);
        return products;
    }
}
