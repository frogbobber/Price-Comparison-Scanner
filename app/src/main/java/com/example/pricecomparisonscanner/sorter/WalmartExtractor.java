package com.example.pricecomparisonscanner.sorter;

import com.example.pricecomparisonscanner.information.ProductInformation;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WalmartExtractor extends ProductExtractor{
    @Override
    public ArrayList<ProductInformation> extractProducts(Document document) {
        ArrayList<ProductInformation> products = new ArrayList<>();

        System.out.println(products);
        Elements elements = document.select("div[class*=search-result-gridview-item clearfix arrange-fill]");//search-result-listview-item Grid

        elements.forEach(e -> {
            products.add(new ProductInformation(
                    e.select("a[class*=product-title-link line-clamp line-clamp-2 truncate-title]").attr("abs:href"),//url
                    e.select("img[data-pnodetype*=item-pimg]").attr("alt"), // name
                    e.select("span[class*=price display-inline-block arrange-fit price price-main]").select("span[class*=visuallyhidden]").html() // price
            ));
        });

        System.out.println("Sorted Walmart");

        //System.out.println("Test"+products);
        return products;
    }
}
