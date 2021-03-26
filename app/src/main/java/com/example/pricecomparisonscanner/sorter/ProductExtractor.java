package com.example.pricecomparisonscanner.sorter;

import com.example.pricecomparisonscanner.information.ProductInformation;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public abstract class ProductExtractor {
        public abstract ArrayList<ProductInformation> extractProducts(Document document);
}
