package com.example.pricecomparisonscanner;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.example.pricecomparisonscanner.sorter.AmazonExtractor;
import com.example.pricecomparisonscanner.sorter.TargetExtractor;
import com.example.pricecomparisonscanner.sorter.WalmartExtractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class WebScraper {

    public static AllProductInformation getProductInformation(String name) throws IOException {
        return new AllProductInformation(
                getAmazonProductInformation("https://www.amazon.com/s?k=" + name + "&ref=nb_sb_noss"),
                getWalmartProductInformation("https://www.walmart.com/search/?cat_id=0&query=" + name),
                getTargetProductInformation("https://www.bestbuy.com/site/searchpage.jsp?st=" + name),    // <-- actually best buy, Target Information: https://www.target.com/s?searchTerm=
                null
        );
    }

    //getBestBuyProductInformation("https://www.bestbuy.com/site/searchpage.jsp?st=" + name),
    //https://www.bestbuy.com/site/searchpage.jsp?st=

    private static ArrayList<ProductInformation> getAmazonProductInformation(String url) throws IOException {

        /*Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .referrer("https://www.amazon.com/")
                .get();*/

        //System.out.println(url);
        //return new AmazonExtractor().extractProducts(document);
        return null;
    }
    private static ArrayList<ProductInformation> getWalmartProductInformation(String url) throws IOException {
        /*Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .referrer("https://www.walmart.com/")
                .get();*/

        //System.out.println(document);
        //return new WalmartExtractor().extractProducts(document);
        return null;
    }
    private static ArrayList<ProductInformation> getTargetProductInformation(String url) throws IOException {

        System.out.println(url);
        Document document = Jsoup.connect("https://www.bestbuy.com/site/searchpage.jsp?st=MONOPOLY+Game")
                .header("Accept", "text/html,application/xhtml+xml,application/xml")
                .header("Accept-Language", "en")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("TE", "Trailers")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36
                .referrer("https://www.bestbuy.com/")
                .get();

        //System.out.println("Testy");
        return new TargetExtractor().extractProducts(document);
        //return null;
    }

    private static ArrayList<ProductInformation> getBestBuyProductInformation(String url) throws IOException{
        return null;
    }

}
