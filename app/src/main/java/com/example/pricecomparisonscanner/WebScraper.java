package com.example.pricecomparisonscanner;

import com.example.pricecomparisonscanner.Sorter.AmazonExtractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class WebScraper {

    public static ArrayList<ProductInformation> getProductInformation(String name) throws IOException {

        return getAmazonProductInformation("https://www.amazon.com/s?k=" + name + "&ref=nb_sb_noss");
    }

    private static ArrayList<ProductInformation> getAmazonProductInformation(String url) throws IOException {

        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .referrer("https://www.amazon.com/")
                .get();

        return new AmazonExtractor().extractProducts(document);
    }

}
