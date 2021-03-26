package com.example.pricecomparisonscanner.Sorter;

import com.example.pricecomparisonscanner.ProductInformation;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public abstract class ProductExtractor {
        public abstract ArrayList<ProductInformation> extractProducts(Document document);
}
