package com.example.pricecomparisonscanner.Sorter;

import com.example.pricecomparisonscanner.ProductInformation;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class AmazonExtractor extends ProductExtractor {
    //private String cssQuery = "span[id*=priceblock_ourprice], span[class*=a-offscreen],span[class*=visuallyhidden],span[class*=a-price],span[id*=saleprice],span.a-size-large.a-color-price.guild_priceblock_ourprice";
    @Override
    public  ArrayList<ProductInformation> extractProducts(Document document) {
        ArrayList<ProductInformation> products = new ArrayList();

        Elements elements = document.select("div[class*=a-section a-spacing-medium]");

        elements.forEach(e -> {
            products.add(new ProductInformation(
                    e.select("span[class*=a-link-normal a-text-normal]").html(), // todo make this get the url
                    e.select("span[class*=a-size-base-plus a-color-base a-text-normal]").html(),
                    e.select("span[class*=a-offscreen]").html()
            ));
        });

        return products;
    }
}
