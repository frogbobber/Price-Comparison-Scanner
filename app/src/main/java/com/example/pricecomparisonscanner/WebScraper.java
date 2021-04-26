package com.example.pricecomparisonscanner;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.example.pricecomparisonscanner.sorter.AmazonExtractor;
import com.example.pricecomparisonscanner.sorter.BestBuyExtractor;
import com.example.pricecomparisonscanner.sorter.TargetExtractor;
import com.example.pricecomparisonscanner.sorter.WalmartExtractor;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class WebScraper {

    public static AllProductInformation getProductInformation(String name, String upc) throws IOException {
        AllProductInformation p = new AllProductInformation(
                getAmazonProductInformation("https://www.amazon.com/s?k=" + name + "&ref=nb_sb_noss"),
                getWalmartProductInformation("https://www.walmart.com/search/?query=" + name),// https://www.walmart.com/search/?cat_id=0&query=
                getTargetProductInformation("https://www.target.com/s?searchTerm=" + name),    // <-- actually best buy, Target Information: https://www.target.com/s?searchTerm= https://www.bestbuy.com/site/searchpage.jsp?st=
                null,
                upc
        );
        return p;
    }

    private static ArrayList<ProductInformation> getAmazonProductInformation(String url) {
        try {
            Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .referrer("https://www.amazon.com/")
                .get();
            return new AmazonExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static ArrayList<ProductInformation> getWalmartProductInformation(String url) {

        url = "https://www.walmart.com/search/?query=sugar%20free%20jello&typeahead=jello";
        try {
            Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36") //Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36
                .header("Accept", "text/html,application/xhtml+xml,application/xml")
                .header("Accept-Language", "en")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests",  "1")
                .header("TE", "Trailers")
                .referrer("https://www.walmart.com/")
                .get();
            return new WalmartExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /*.header("Accept", "text/html,application/xhtml+xml,application/xml")
               .header("Accept-Language", "en")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests",  "1")
                .header("TE", "Trailers")*/
    private static ArrayList<ProductInformation> getTargetProductInformation(String url) {
        url = "https://www.target.com/s?searchTerm=jello";
        try {
            Document document = Jsoup.connect(url)
                .header("Accept", "text/html,application/xhtml+xml,application/xml")
                .header("Accept-Language", "en")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("TE", "Trailers")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36
                .referrer("https://www.target.com/")
                .get();
            return new TargetExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static ArrayList<ProductInformation> getBestBuyProductInformation(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml")
                    .header("Accept-Language", "en")
                    .header("Accept-Encoding", "gzip,deflate,sdch")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("TE", "Trailers")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36
                    .referrer("https://www.bestbuy.com/")
                    .get();
            return new BestBuyExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
